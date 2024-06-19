package com.microservice_photo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPhotoService {
    String uploadImage(MultipartFile file) throws IOException;
    byte[] findImageFromDatabase(String fileName);
    String uploadImageToFileSystem(MultipartFile file) throws IOException;
    byte[] findImageFromFileSystem(String fileName) throws IOException;

    String updateImage(String fileName, MultipartFile file) throws IOException;
    String updateImageInFileSystem(String fileName, MultipartFile file) throws IOException;
    void deleteImageFromFileSystem(String fileName);
    void deleteImage(String fileName);

}
