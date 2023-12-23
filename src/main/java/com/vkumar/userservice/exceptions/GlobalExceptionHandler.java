package com.vkumar.userservice.exceptions;

import com.vkumar.userservice.responses.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String errMsg = ex.getMessage();
        ApiErrorResponse response = ApiErrorResponse.builder().message(errMsg)
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handleOperationFailedException(OperationFailedException ex) {
        String errMsg = ex.getMessage();
        ApiErrorResponse response = ApiErrorResponse.builder().message(errMsg)
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .success(false)
                .build();
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
