package com.microservice_photo.persistence;

import com.microservice_photo.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFileDataRepository extends JpaRepository<FileData, Long> {

    @Query("SELECT f FROM FileData f WHERE f.name LIKE %:keyword%")
    List<FileData> findByNameContains(@Param("keyword") String keyword);
    Optional<FileData> findByName(String fileName);
}
