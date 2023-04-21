package com.example.cleanapplication

data class RegistrationFormState(
    val firstName: String = "",
    val firstNameError:String? = null,
    val lastName: String = "",
    val lastNameError:String? = null,
    val mobileNumber: String = "",
    val mobileNumberError:String? = null,
    val email: String = "",
    val emailError:String? = null,
    val birthdate: String = "",
    val birthdateError:String? = null,
    val password: String = "",
    val passwordError:String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError:String? = null,
    val acceptedTerms:Boolean = false,
    val termsError:String? = null
)
