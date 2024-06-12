package com.microservices_user.service;

import com.microservices_user.dto.UserDTO;
import com.microservices_user.dto.UserLoginDTO;
import com.microservices_user.entities.Role;
import com.microservices_user.entities.Status;
import com.microservices_user.entities.User;
import com.microservices_user.exception.UserAlreadyExist;
import com.microservices_user.exception.UserNotFoundException;
import com.microservices_user.mapper.IUserLoginMapper;
import com.microservices_user.mapper.IUserMapper;
import com.microservices_user.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository userRepository;
    private final IUserMapper userMapper = IUserMapper.INSTANCE;
    private final IUserLoginMapper userLoginMapper = IUserLoginMapper.INSTANCE;
    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getStatus() != Status.DELETED)
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElse(null);
    }

    @Override
    public List<UserDTO> findByName(String name) {
        return userRepository.findByName(name).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByRole(String roleName) {
        Role role = Role.valueOf(roleName.toUpperCase());
        return userRepository.findByRole(role).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByStatus(String statusName) {
        Status status = Status.valueOf(statusName.toUpperCase());
        return userRepository.findByStatus(status).stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        // Asegúrate de que userDTO tiene un DNI y que es único
        if (userDTO.getId() == null || userRepository.findById(userDTO.getId()).isPresent()) {
            throw new UserAlreadyExist();
        }
        user.setId(userDTO.getId());

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        User updatedUser = IUserMapper.INSTANCE.userDTOToUser(userDTO);
        updatedUser.setId(user.getId()); // Mantén el ID original

        return userRepository.save(updatedUser);
    }

    @Override
    public User updateStatus(Long id, String status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setStatus(Status.valueOf(status));

        return userRepository.save(user);
    }

    @Override
    public User updateRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setRole(Role.valueOf(role));

        return userRepository.save(user);
    }

    @Override
    public User saveCredentials(Long id, UserLoginDTO userLoginDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        User userWithNewCredentials = IUserLoginMapper.INSTANCE.toUser(userLoginDTO);
        userWithNewCredentials.setId(user.getId()); // Mantén el ID original

        return userRepository.save(userWithNewCredentials);
    }

    @Override
    public User updateCredentials(Long id, UserLoginDTO userLoginDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(userLoginDTO.getUsername());
        user.setPassword(userLoginDTO.getPassword());

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }
}
