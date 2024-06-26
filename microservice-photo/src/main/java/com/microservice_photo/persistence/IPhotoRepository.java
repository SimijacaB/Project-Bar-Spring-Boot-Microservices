package com.microservice_photo.persistence;

import com.microservice_photo.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findByName(String fileName);
}
