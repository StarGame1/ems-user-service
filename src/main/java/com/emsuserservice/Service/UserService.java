package com.emsuserservice.Service;

import com.emsuserservice.Entity.User;
import com.emsuserservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
