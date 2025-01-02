package com.example.identity.Controller;

import com.example.identity.DTO.Request.ApiResponse;
import com.example.identity.DTO.Request.UserCreation;
import com.example.identity.DTO.Request.UserUpdate;
import com.example.identity.DTO.Response.UpdateUserResponse;
import com.example.identity.DTO.Response.UserResponse;
import com.example.identity.Repository.UserRepository;
import com.example.identity.Service.UserService;
import com.example.identity.entity.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    ApiResponse<User> createUser(@RequestBody @Valid UserCreation userCreation) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreation));
        return apiResponse;
    }

    @GetMapping()
    ApiResponse<List<User>> getAllUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        var userList = userService.getAllUsers();

        return ApiResponse.<List<User>>builder()
                .result(userList)
                .build();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdate userUpdate) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUserById(userId, userUpdate));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    void deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInformation())
                .build();
    }
}
