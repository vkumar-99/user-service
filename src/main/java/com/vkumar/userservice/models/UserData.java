package com.vkumar.userservice.models;

public record UserData(
        String userId,
        String name,
        String email,
        String about) {
}
