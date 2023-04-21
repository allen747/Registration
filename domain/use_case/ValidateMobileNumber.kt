package com.example.cleanapplication.domain.use_case

class ValidateMobileNumber {

    fun execute(mobileNumber:String): ValidationResult {
        if (mobileNumber.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Mobile number is required"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}