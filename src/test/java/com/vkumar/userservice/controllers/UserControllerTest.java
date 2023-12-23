package com.vkumar.userservice.controllers;

import com.vkumar.userservice.controller.UserController;
import com.vkumar.userservice.models.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class UserControllerTest {

    private UserController controller;

    @BeforeEach
    void init() {
        controller = new UserController();
    }

    // The method successfully creates a new user with valid input data.
    @Test
    public void test_createUser_validInput() {
        UserData data = new UserData("123", "John Doe", "john@example.com", "About John");
        ResponseEntity<UserData> response = controller.createUser(data);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(data, response.getBody());
    }

    // The method returns a response with HTTP status code 201 when a new user is created.
    @Test
    public void test_createUser_statusCode201() {
        UserData data = new UserData("123", "John Doe", "john@example.com", "About John");
        ResponseEntity<UserData> response = controller.createUser(data);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // The method returns the created user data in the response body when a new user is created.
    @Test
    public void test_createUser_responseBody() {
        UserData data = new UserData("123", "John Doe", "john@example.com", "About John");
        ResponseEntity<UserData> response = controller.createUser(data);
        assertEquals(data, response.getBody());
    }

    // The method returns a response with HTTP status code 400 when the input data is invalid.
    @Test
    public void test_createUser_invalidInput() {
        UserData data = new UserData(null, "John Doe", "john@example.com", "About John");
        ResponseEntity<UserData> response = controller.createUser(data);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // The method throws an exception when the input data is null.
    @Test
    public void test_createUser_nullInput() {
        UserData data = null;
        assertThrows(NullPointerException.class, () -> {
            controller.createUser(data);
        });
    }

}
