package com.emsuserservice.Controller;

import com.emsuserservice.Dto.CreateUserRequest;
import com.emsuserservice.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/users")
@CrossOrigin(origins = "*")
public class InternalUserController {

    private final UserService userService;

    public InternalUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createFromAuth(@RequestBody CreateUserRequest dto) {
        userService.createFromAuth(dto);
    }
}

