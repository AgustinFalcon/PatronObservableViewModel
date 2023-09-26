package com.example.patronobservable

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class MainViewModel: ViewModel() {




    private val _viewState = MutableLiveData<RegisterStates>()
    val viewState: LiveData<RegisterStates> get() = _viewState

    private var email: String = ""
    private var password: String = ""
    private var repeatPass: String = ""


    private fun validateButtons() {
        if (PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && (password == repeatPass && password.isNotEmpty() && repeatPass.isNotEmpty())) {
            _viewState.value = RegisterStates.BtnSuccess
        } else {
            _viewState.value = RegisterStates.BtnError
        }
    }


    fun validateEmail(email: String) {
        if (email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            _viewState.value = RegisterStates.SuccessEmailField
            this.email = email
        } else {
            _viewState.value = RegisterStates.ErrorEmailField(email = "Ingrese un email valido")
        }
        validateButtons()
    }


    fun validatePass(password: String) {
        if (password.isNotEmpty() && password.length >= 3) {
            _viewState.value = RegisterStates.SuccessPassField
            this.password = password
        } else {
            _viewState.value = RegisterStates.ErrorPassField(password = password)
        }
        validateButtons()
    }


    fun validateRepeatPass(repeatPass: String) {
        if (password == repeatPass) {
            _viewState.value = RegisterStates.SuccessRepeatPassField
            this.repeatPass = repeatPass
        } else {
            _viewState.value = RegisterStates.ErrorRepeatPassField(message = "Las contraseñas son distintas")
        }
        validateButtons()
    }


}


sealed class RegisterStates() {
    object SuccessEmailField: RegisterStates()
    data class ErrorEmailField(val email: String): RegisterStates()
    object SuccessPassField: RegisterStates()
    data class ErrorPassField(val password: String): RegisterStates()
    object SuccessRepeatPassField: RegisterStates()
    data class ErrorRepeatPassField(val message: String): RegisterStates()
    object BtnSuccess: RegisterStates()
    object BtnError: RegisterStates()
}