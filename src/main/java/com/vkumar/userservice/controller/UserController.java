package com.vkumar.userservice.controller;

import com.vkumar.userservice.models.UserRequest;
import com.vkumar.userservice.models.responses.UserDataResponse;
import com.vkumar.userservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDataResponse> createUser(@Valid @RequestBody UserRequest data) {
        UserDataResponse result = userService.addUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAlluser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDataResponse> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<String> cleanDb() {
        userService.cleanDb();
        return ResponseEntity.ok("Db Cleaned");
    }
}
