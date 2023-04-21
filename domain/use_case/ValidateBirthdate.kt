package com.example.cleanapplication.domain.use_case

class ValidateBirthdate {

    fun execute(birthdate:String): ValidationResult {
        if (birthdate.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Birthdate is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}