package com.example.cleanapplication.domain.use_case

class ValidateLastName {

    fun execute(lastName:String): ValidationResult {
        if (lastName.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Last name is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}