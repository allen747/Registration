package com.example.cleanapplication.domain.use_case

import android.util.Patterns

class ValidatePassword {
    fun execute(password:String): ValidationResult {
        if (password.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}