package com.vkumar.userservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_data")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;
    private String email;
    private String about;
}
