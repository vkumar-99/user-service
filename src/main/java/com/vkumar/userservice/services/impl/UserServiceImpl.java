package com.vkumar.userservice.services.impl;

import com.vkumar.userservice.entities.User;
import com.vkumar.userservice.exceptions.OperationFailedException;
import com.vkumar.userservice.exceptions.ResourceNotFoundException;
import com.vkumar.userservice.models.UserData;
import com.vkumar.userservice.repositories.UserRepository;
import com.vkumar.userservice.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserData addUser(UserData user) {
        User result = userRepository.save(convertToEntity(user));
        if (Objects.isNull(result))
            throw new OperationFailedException("Add user operation failed!!!");
        return user;
    }

    @Override
    public UserData getUser(String userId) {
        User userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id: "+userId));
        return convertToRecord(userEntity);
    }

    @Override
    public List<UserData> getAlluser() {
        return userRepository.findAll().stream()
                .map(this::convertToRecord)
                .toList();
    }

    private User convertToEntity(UserData userData) {
        User userEntity = new User();
        BeanUtils.copyProperties(userData, userEntity);
        return userEntity;
    }

    private UserData convertToRecord(User userEntity) {
        return new UserData(userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAbout());
    }
}
