package com.microservices_user.mapper;

import com.microservices_user.dto.UserDTO;
import com.microservices_user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
@Mapping(target = "id",source = "id")
@Mapping(target = "name", source = "name")
@Mapping(target = "status", ignore = true)
@Mapping(target = "role", source = "role")
UserDTO userToUserDTO(User user);

@Mapping(target = "id",source = "id")
@Mapping(target = "name", source = "name")
@Mapping(target = "status", ignore = true)
@Mapping(target = "role", source = "role")
User userDTOToUser(UserDTO userDTO);




}
