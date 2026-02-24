package com.example.demo.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class ApiResponse <T> {

    private T data;

    private String message;

    private Map<String, List<String>> errors;

    // Success response
    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    // Message response
    public ApiResponse (String message) {
        this.message = message;
    }

    // Error response
    public ApiResponse(Map<String, List<String>> errors) {
        this.errors = errors != null ? errors : Collections.emptyMap();
    }
}
