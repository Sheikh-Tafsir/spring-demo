package com.example.demo.exception;

import jakarta.persistence.NoResultException;

public class HasibulNotFound extends NoResultException {
    public HasibulNotFound(String message) {
        super(message);
    }
}
