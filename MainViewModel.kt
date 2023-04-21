package com.example.cleanapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanapplication.domain.use_case.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateMobileNumber: ValidateMobileNumber = ValidateMobileNumber(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateBirthdate: ValidateBirthdate = ValidateBirthdate(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateConfirmPassword = ValidateConfirmPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent){
        when(event) {
            is RegistrationFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is RegistrationFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is RegistrationFormEvent.MobileNumberChanged -> {
                state = state.copy(mobileNumber = event.mobileNumber)
            }
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.BirthdateChanged -> {
                state = state.copy(birthdate = event.birthdate)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }
            is RegistrationFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val mobileNumberResult = validateMobileNumber.execute(state.mobileNumber)
        val emailResult = validateEmail.execute(state.email)
        val birthdateResult = validateBirthdate.execute(state.birthdate)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPassword = validateConfirmPassword.execute(state.password, state.confirmPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            mobileNumberResult,
            emailResult,
            birthdateResult,
            passwordResult,
            confirmPassword,
            termsResult
        ).any{!it.successful }

        if (hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                mobileNumberError = mobileNumberResult.errorMessage,
                emailError = emailResult.errorMessage,
                birthdateError = birthdateResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPassword.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}