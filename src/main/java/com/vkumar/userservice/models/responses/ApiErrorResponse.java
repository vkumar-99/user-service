package com.vkumar.userservice.models.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

}
