package com.vkumar.userservice.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRequest {

    @NotBlank(message = "Name cannot be empty")
    @Min(3)
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "About cannot be empty")
    @Min(3)
    private String about;
}
