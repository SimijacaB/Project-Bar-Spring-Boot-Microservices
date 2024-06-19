package com.microservice_photo.service;

import com.microservice_photo.entities.FileData;
import com.microservice_photo.entities.Photo;
import com.microservice_photo.persistence.IFileDataRepository;
import com.microservice_photo.persistence.IStorageRepository;
import com.microservice_photo.util.PhotoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements IPhotoService {
    @Autowired
    private IStorageRepository photoRepository;
    @Autowired
    private IFileDataRepository fileDataRepository;
    private final String FOLDER_PATH = "C:\\Users\\Yesika Simijaca\\Desktop\\Imagenes\\";

    //MultipartFile es una interfaz que proporciona acceso a los datos de un archivo de carga.
    // La implementación está basada en Apache Commons FileUpload.
    // La interfaz puede utilizarse para obtener el nombre original del archivo, el tipo de contenido del archivo, el tamaño del archivo, etc.
    // MultipartFile proporciona métodos para transferir el archivo a un archivo en el sistema de archivos del servidor.
    // La interfaz también proporciona métodos para leer los datos del archivo.
    // La interfaz MultipartFile se utiliza en un controlador de Spring MVC para manejar la carga de archivos.
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Optional<Photo> dbImageData = photoRepository.findByName(file.getOriginalFilename());
        if (dbImageData.isPresent()) {
            return "file already exists : " + file.getOriginalFilename();
        }

        Photo photo = photoRepository.save(Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(PhotoUtils.compressImage(file.getBytes())).build());
        if (photo != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] findImageFromDatabase(String fileName) {
        Optional<Photo> dbImageData = photoRepository.findByName(fileName);
        byte[] images = PhotoUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    @Override
    public String uploadImageToFileSystem(MultipartFile file) {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        try {
            FileData fileData = fileDataRepository.save(FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .filePath(filePath).build());

            file.transferTo(new File(filePath));

            if (fileData != null) {
                return "file uploaded successfully : " + filePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] findImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Override
    public String updateImage(String fileName, MultipartFile file) throws IOException {
        if (fileName == null || file == null) {
            return "file name is null";
        }

        if (!fileDataRepository.findByName(fileName).isPresent()) {
            return "file not found";
        }
        Photo photo = photoRepository.findByName(fileName).get();
        photo.setId(photo.getId());
        photo.setName(file.getOriginalFilename());
        photo.setType(file.getContentType());
        photoRepository.save(photo);

        return "file updated successfully : " + fileName;

    }

    @Override
    public String updateImageInFileSystem(String fileName, MultipartFile file) throws IOException {
        if (fileName == null || file == null) {
            return "file name is null";
        }

        if (!fileDataRepository.findByName(fileName).isPresent()) {
            return "file not found";
        }
        FileData fileData = fileDataRepository.findByName(fileName).get();
        fileData.setId(fileData.getId());
        fileData.setName(file.getOriginalFilename());
        fileData.setType(file.getContentType());
        fileData.setFilePath(FOLDER_PATH + file.getOriginalFilename());
        fileDataRepository.save(fileData);

        return "file updated successfully : " + fileName;


    }

    @Override
    public void deleteImageFromFileSystem(String fileName) {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        if (fileData.isPresent()) {
            File file = new File(fileData.get().getFilePath());
            file.delete();
            fileDataRepository.delete(fileData.get());
        }

    }

    @Override
    public void deleteImage(String fileName) {
        Optional<Photo> photo = photoRepository.findByName(fileName);
        if (photo.isPresent()) {
            photoRepository.delete(photo.get());
        }
    }


}
