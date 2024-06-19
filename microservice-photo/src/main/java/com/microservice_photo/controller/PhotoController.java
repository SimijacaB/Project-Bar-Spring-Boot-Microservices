package com.microservice_photo.controller;

import com.microservice_photo.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private IPhotoService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.findImageFromDatabase(fileName);
        //Content-Type: image/png hace que el navegador entienda que es una imagen
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = service.findImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }


    @PutMapping("/{fileName}")
    public ResponseEntity<?> updateImage(@PathVariable String fileName, @RequestParam("image") MultipartFile file) throws IOException {
        String updateImage = service.updateImage(fileName, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateImage);
    }

    @PutMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> updateImageInFileSystem(@PathVariable String fileName, @RequestParam("image") MultipartFile file) throws IOException {
        String updateImage = service.updateImageInFileSystem(fileName, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateImage);
    }
    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) {
        service.deleteImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Image deleted successfully");
    }

    @DeleteMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> deleteImageFromFileSystem(@PathVariable String fileName) {
        service.deleteImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Image deleted successfully");
    }
}
