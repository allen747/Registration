package com.example.cleanapplication.domain.use_case

import android.util.Patterns

class ValidateConfirmPassword {

    fun execute(password:String, confirmPassword: String): ValidationResult {
        if (password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "the passwords don't match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}