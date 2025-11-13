package com.emsuserservice.Dto;

public record AdminCreateUserRequest(
        String username,
        String role,
        String password
) {}
