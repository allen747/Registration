package com.example.cleanapplication.domain.use_case

import android.util.Patterns

class ValidateFirstName {

    fun execute(firstName:String): ValidationResult {
        if (firstName.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "First name is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}