package com.example.identity.Mapper;

import com.example.identity.DTO.Request.PermissionRequest;
import com.example.identity.DTO.Request.UserCreation;
import com.example.identity.DTO.Response.PermissionResponse;
import com.example.identity.DTO.Response.UpdateUserResponse;
import com.example.identity.entity.Permission;
import com.example.identity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission request);
}
