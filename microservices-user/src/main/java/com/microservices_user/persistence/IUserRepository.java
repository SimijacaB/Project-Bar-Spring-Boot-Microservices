package com.microservices_user.persistence;

import com.microservices_user.entities.Role;
import com.microservices_user.entities.Status;
import com.microservices_user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    /**
     * Esta query permite buscar una lista de usuarios que contengan el nombre ingresado
     */
    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
    List<User> findByName(String name);

    /**
     * Esta query permite buscar una lista de usuarios que tengan el rol ingresado
     */
    @Query("SELECT u FROM User u WHERE u.role = ?1")
    List<User> findByRole(Role role);

    /**
     * Esta query permite buscar una lista de usuarios que tengan el estado ingresado
     */
    @Query("SELECT u FROM User u WHERE u.status = ?1")
    List<User> findByStatus(Status status);









}
