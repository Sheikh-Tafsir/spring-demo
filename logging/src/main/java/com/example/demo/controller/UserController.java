package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.JsrValidationException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(@RequestParam(required = false) int pageno, @RequestParam(required = false) int pagesize) {
        return ResponseEntity.ok(userService.findAll(pageno, pagesize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(user, "User found"));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult bindingResult) {
        userValidator.validateUser(user, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new JsrValidationException(bindingResult);
        }

        userService.save(user);
        return ResponseEntity.ok(null);
    }


}
