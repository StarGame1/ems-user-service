package com.emsuserservice.Service;

import com.emsuserservice.Dto.AdminCreateUserRequest;
import com.emsuserservice.Dto.CreateUserRequest;
import com.emsuserservice.Entity.Role;
import com.emsuserservice.Entity.User;
import com.emsuserservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;         // <-- import corect
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Value("${device.service.url}")
    private String deviceServiceUrl;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public User saveFromAdmin(AdminCreateUserRequest dto) {

        User user = new User();
        user.setUsername(dto.username());
        user.setRole(Role.valueOf(dto.role()));

        User saved = userRepository.save(user);

        syncToAuth(dto);

        return saved;
    }
    public void syncToAuth(AdminCreateUserRequest dto) {
        try {
            restTemplate.postForLocation(authServiceUrl, dto);
        } catch (Exception e) {
            System.out.println("Could not sync user to auth-service: " + e.getMessage());
        }
    }



    //Create
    public User save(User user) {
        return userRepository.save(user);
    }

    //Read
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Read by id
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    //Read by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    //Update by
    public User update(Long id, User updatedUser) {
        User existingUser = findById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    //Delete
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("user not found");
        }
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createFromAuth(CreateUserRequest dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElse(null);

        if (user == null) {
            user = new User();

        }

        user.setUsername(dto.username());
        user.setRole(Role.valueOf(dto.role()));

        userRepository.save(user);

        try {
            restTemplate.postForLocation(deviceServiceUrl, dto);
        } catch (Exception e) {
            System.out.println("Could not sync user to device-service: " + e.getMessage());
        }
    }


}
