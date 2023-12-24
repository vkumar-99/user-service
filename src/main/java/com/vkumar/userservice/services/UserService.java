package com.vkumar.userservice.services;

import com.vkumar.userservice.models.UserRequest;
import com.vkumar.userservice.models.response.UserDataResponse;

import java.util.List;

public interface UserService {

    UserDataResponse addUser(UserRequest user);

    UserDataResponse getUser(String userId);

    List<UserDataResponse> getAlluser();
}
