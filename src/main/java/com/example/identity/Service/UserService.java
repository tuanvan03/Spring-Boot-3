package com.example.identity.Service;

import com.example.identity.DTO.Request.UserCreation;
import com.example.identity.DTO.Request.UserUpdate;
import com.example.identity.DTO.Response.UpdateUserResponse;
import com.example.identity.DTO.Response.UserResponse;
import com.example.identity.Enums.EnumRoles;
import com.example.identity.Exception.AppException;
import com.example.identity.Exception.ErrorCode;
import com.example.identity.Mapper.UserMapper;
import com.example.identity.Repository.RoleRepository;
import com.example.identity.Repository.UserRepository;
import com.example.identity.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public User createUser(UserCreation userCreation) {
//        User user = new User();

        // If user exists
        if (userRepository.existsByUsername(userCreation.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITS);
        }

//        // builder
//        UserCreation user = UserCreation.builder()
//                .username(userCreation.getUsername())
//                .lastName(userCreation.getLastName())
//                .build();
//
        // Create a new user
//        user.setUsername(userCreation.getUsername());
//        user.setFirstname(userCreation.getFirstname());
//        user.setLastName(userCreation.getLastName());
//        user.setPassword(userCreation.getPassword());
//        user.setDob(userCreation.getDob());

        // Mapper
        User user = userMapper.toUser(userCreation);

        // Hash password
        user.setPassword(passwordEncoder.encode(userCreation.getPassword()));

        // Set user's role, default is USER
        HashSet<String> roles = new HashSet<>();
        roles.add(EnumRoles.USER.name());
//        user.setRoles(roles);

        // Update database
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN)")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User is not found"));
    }

    public UserResponse updateUserById(String userId, UserUpdate userUpdate) {
        User user = getUserById(userId);

        //update
//        user.setUsername(userUpdate.getUsername());
//        user.setFirstname(userUpdate.getFirstname());
//        user.setLastName(userUpdate.getLastName());
//        user.setPassword(userUpdate.getPassword());
//        user.setDob(userUpdate.getDob());
        userMapper.updateUser(user, userUpdate);
        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));

        var roles = roleRepository.findAllById(userUpdate.getRoles());
        user.setRoles(new HashSet<>(roles));

        UserResponse updateUserResponse = userMapper.toUserResponse(user);
        userRepository.save(user);
        return updateUserResponse;
    }

    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInformation() {
        // get current user
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user =  userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITS));

        return userMapper.toUserResponse(user);
    }
}
