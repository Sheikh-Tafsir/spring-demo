package com.example.demo.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class JsrValidationException extends RuntimeException {
    private final Map<String, List<String>> errors;

    public JsrValidationException(BindingResult bindingResult) {
        super("Validation failed");
        this.errors = new LinkedHashMap<>();

        bindingResult.getFieldErrors().forEach(f ->
                this.errors.computeIfAbsent(f.getField(), k -> new ArrayList<>()).add(f.getDefaultMessage())
        );

        bindingResult.getGlobalErrors().forEach(g ->
                this.errors.computeIfAbsent(g.getObjectName(), k -> new ArrayList<>()).add(g.getDefaultMessage())
        );
    }
}
