package com.microservices_user.service;

import com.microservices_user.dto.UserDTO;
import com.microservices_user.dto.UserLoginDTO;
import com.microservices_user.entities.User;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    List<UserDTO> findByName(String name);
    List<UserDTO> findByRole(String role);
    List<UserDTO> findByStatus(String status);
    User save (UserDTO userDTO);
    User update (Long id, UserDTO userDTO);
    User updateStatus (Long id, String status);
    User updateRole (Long id, String role);
    User saveCredentials (Long id, UserLoginDTO userLoginDTO);
    User updateCredentials (Long id, UserLoginDTO userLoginDTO);
    void delete (Long id);
}
