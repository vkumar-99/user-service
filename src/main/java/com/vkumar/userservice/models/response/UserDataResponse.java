package com.vkumar.userservice.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
    private String userName;
    private String email;
    private String about;
    private List<Rating> ratings;
}
