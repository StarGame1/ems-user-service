package com.emsuserservice.Dto;

public record CreateUserRequest(
        Long id,
        String username,
        String role
) {}
