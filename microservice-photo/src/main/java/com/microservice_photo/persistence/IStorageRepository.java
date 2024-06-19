package com.microservice_photo.persistence;

import com.microservice_photo.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IStorageRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.name LIKE %:keyword%")
    List<Photo> findByNameContains( String keyword);
    Optional<Photo> findByName(String fileName);
}
