package com.microservices_user.mapper;

import com.microservices_user.dto.UserLoginDTO;
import com.microservices_user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserLoginMapper {

    IUserLoginMapper INSTANCE = Mappers.getMapper(IUserLoginMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    UserLoginDTO toUserLoginDTO(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    User toUser(UserLoginDTO userLoginDTO);
}
