package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    @Size(min = 3, max = 32)
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
