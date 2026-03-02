package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HasibulNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleHasibulNotFoundtException(HasibulNotFound ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResultException(NoResultException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ApiResponse<>(getErrorsFromException(ex)), HttpStatusCode.valueOf(422));
    }

    @ExceptionHandler(JsrValidationException.class)
    public ResponseEntity<?> handleJsrValidationExceptions(JsrValidationException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getErrors()), HttpStatusCode.valueOf(422));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Map<String, List<String>> getErrorsFromException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.computeIfAbsent(error.getField(), key -> new ArrayList<>())
                    .add(error.getDefaultMessage());
        }

        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            errors.computeIfAbsent(objectError.getObjectName(), key -> new ArrayList<>())
                    .add(objectError.getDefaultMessage());
        }

        return errors;
    }
}
