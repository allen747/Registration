package com.example.cleanapplication.domain.use_case

sealed class RegistrationFormEvent{
    data class FirstNameChanged(val firstName : String): RegistrationFormEvent()
    data class LastNameChanged(val lastName : String): RegistrationFormEvent()
    data class MobileNumberChanged(val mobileNumber : String): RegistrationFormEvent()
    data class EmailChanged(val email : String): RegistrationFormEvent()
    data class BirthdateChanged(val birthdate : String): RegistrationFormEvent()
    data class PasswordChanged(val password : String): RegistrationFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword : String): RegistrationFormEvent()
    data class AcceptTerms(val isAccepted : Boolean): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
