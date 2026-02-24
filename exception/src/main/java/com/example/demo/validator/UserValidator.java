package com.example.demo.validator;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator {

    public void validateUser(User user, Errors errors) {
        if (!hasUppercaseAndNumber(user.getPassword())) {
            errors.rejectValue("password", "", "Password must contain at least one uppercase letter and one number");
        }
    }

    public static boolean hasUppercaseAndNumber(String input) {
        boolean hasUppercase = false;
        boolean hasNumber = false;

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            // If both conditions are met, no need to continue
            if (hasUppercase && hasNumber) {
                return true;
            }
        }

        return false;
    }
}
