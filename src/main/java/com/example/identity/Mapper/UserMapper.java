package com.example.identity.Mapper;

import com.example.identity.DTO.Request.UserCreation;
import com.example.identity.DTO.Request.UserUpdate;
import com.example.identity.DTO.Response.UpdateUserResponse;
import com.example.identity.DTO.Response.UserResponse;
import com.example.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreation userCreation);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdate userUpdate);
}
