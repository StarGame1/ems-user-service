package com.emsuserservice.Controller;

import com.emsuserservice.Entity.User;
import com.emsuserservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
            if(userService.existsByUsername(user.getUsername())){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            User saved = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(id, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
