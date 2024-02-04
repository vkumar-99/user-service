package com.vkumar.userservice.services.impl;

import com.vkumar.userservice.entities.User;
import com.vkumar.userservice.exceptions.OperationFailedException;
import com.vkumar.userservice.exceptions.ResourceNotFoundException;
import com.vkumar.userservice.external.HotelClient;
import com.vkumar.userservice.external.RatingClient;
import com.vkumar.userservice.models.UserRequest;
import com.vkumar.userservice.models.responses.Hotel;
import com.vkumar.userservice.models.responses.Rating;
import com.vkumar.userservice.models.responses.UserDataResponse;
import com.vkumar.userservice.repositories.UserRepository;
import com.vkumar.userservice.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelClient hotelClient;
    @Autowired
    private RatingClient ratingClient;

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

    @Override
    public void cleanDb() {
        userRepository.deleteAll();
    }

    private List<Rating> getUserRating(String userId) {
        //Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + userId, Rating[].class);
        List<Rating> ratingsList = ratingClient.getRatingsByUserId(userId);
        if(ratingsList.size() == 0) {
            return Collections.emptyList();
        }
        ratingsList = ratingsList.stream().map(rating -> {
            Hotel hotel = hotelClient.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).toList();
        return ratingsList;
    }
    private User convertToEntity(UserRequest userData) {
        User userEntity = new User();
        BeanUtils.copyProperties(userData, userEntity);
        return userEntity;
    }

    private UserDataResponse convertToRecord(User userEntity) {
        List<Rating> userRatings = getUserRating(userEntity.getUserId());
        return new UserDataResponse(userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAbout(),
                userRatings);
    }
}
