package com.fitness.user_service.service;

import com.fitness.user_service.dto.RegisterRequest;
import com.fitness.user_service.dto.UserResponse;
import com.fitness.user_service.model.User;
import com.fitness.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse register(RegisterRequest request) {

        String email = request.getEmail();

        if(userRepository.existsByEmail(email)){
            User existingUser = userRepository.findByEmail(email);

            UserResponse userResponse = new UserResponse();

            userResponse.setId(existingUser.getId());
            userResponse.setEmail(existingUser.getEmail());
            userResponse.setFirstName(existingUser.getFirstName());
            userResponse.setLastName(existingUser.getLastName());
            userResponse.setCreatedAt(existingUser.getCreatedAt());
            userResponse.setUpdatedAt(existingUser.getUpdatedAt());
            userResponse.setPassword(existingUser.getPassword());

            return userResponse;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setKeycloakId(request.getKeycloakId());

        User savedUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse();

        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setKeycloakId(savedUser.getKeycloakId());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        userResponse.setPassword(savedUser.getPassword());

        return userResponse;
    }

    public UserResponse getUserProfile(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found..."));

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setPassword(user.getPassword());

        return userResponse;
    }

    public List<UserResponse> findAll(){
        List<User> allUsers = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        for(User user: allUsers){
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());
            userResponse.setPassword(user.getPassword());
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    public boolean existByUserId(String userId) {
        log.info("Calling user service for {}", userId);
        return userRepository.existsByKeycloakId(userId);
    }
}
