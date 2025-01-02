package com.example.identity.Mapper;

import com.example.identity.DTO.Request.PermissionRequest;
import com.example.identity.DTO.Request.RoleRequest;
import com.example.identity.DTO.Response.PermissionResponse;
import com.example.identity.DTO.Response.RoleResponse;
import com.example.identity.entity.Permission;
import com.example.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
