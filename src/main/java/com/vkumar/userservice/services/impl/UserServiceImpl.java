package com.vkumar.userservice.services.impl;

import com.vkumar.userservice.entities.User;
import com.vkumar.userservice.exceptions.OperationFailedException;
import com.vkumar.userservice.exceptions.ResourceNotFoundException;
import com.vkumar.userservice.models.UserRequest;
import com.vkumar.userservice.models.response.UserDataResponse;
import com.vkumar.userservice.repositories.UserRepository;
import com.vkumar.userservice.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDataResponse addUser(UserRequest user) {
        User result = userRepository.save(convertToEntity(user));
        if (Objects.isNull(result))
            throw new OperationFailedException("Add user operation failed!!!");
        return convertToRecord(result);
    }

    @Override
    public UserDataResponse getUser(String userId) {
        User userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id: "+userId));
        return convertToRecord(userEntity);
    }

    @Override
    public List<UserDataResponse> getAlluser() {
        return userRepository.findAll().stream()
                .map(this::convertToRecord)
                .toList();
    }

    private User convertToEntity(UserRequest userData) {
        User userEntity = new User();
        BeanUtils.copyProperties(userData, userEntity);
        return userEntity;
    }

    private UserDataResponse convertToRecord(User userEntity) {
        return new UserDataResponse(userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAbout(),
                Collections.emptyList());
    }
}
