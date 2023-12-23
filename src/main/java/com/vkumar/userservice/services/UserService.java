package com.vkumar.userservice.services;

import com.vkumar.userservice.models.UserData;

import java.util.List;

public interface UserService {

    UserData addUser(UserData user);

    UserData getUser(String userId);

    List<UserData> getAlluser();
}
