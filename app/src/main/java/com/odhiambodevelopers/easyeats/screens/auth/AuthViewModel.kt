package com.odhiambodevelopers.easyeats.screens.auth

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.odhiambodevelopers.easyeats.data.repository.AuthRepository
import com.odhiambodevelopers.easyeats.model.User
import com.odhiambodevelopers.easyeats.utils.Constants.MIN_PASSWORD_LENGTH
import com.odhiambodevelopers.easyeats.utils.Constants.MIN_PHONE_LENGTH
import com.odhiambodevelopers.easyeats.utils.Event
import com.odhiambodevelopers.easyeats.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    //register
    private val _register = mutableStateOf(AuthState())
    val register:State<AuthState> = _register

    private val _nameState = mutableStateOf("")
    val nameState:State<String> = _nameState

    private val _emailState = mutableStateOf("")
    val emailState:State<String> = _emailState

    private val _phoneState = mutableStateOf("")
    val phoneState:State<String> = _phoneState

    private val _passwordState = mutableStateOf("")
    val passwordState:State<String> = _passwordState

    private val _confirmPasswordState = mutableStateOf("")
    val confirmPasswordState:State<String> = _confirmPasswordState


    fun setName(value: String){
        _nameState.value = value
    }

    fun setEmail(value: String){
        _emailState.value = value
    }
    fun setPhone(value: String){
        _phoneState.value = value
    }

    fun setPassword(value: String){
        _passwordState.value = value
    }

    fun setConfirmPassword(value: String){
        _confirmPasswordState.value = value
    }

    //login
    private val _login = mutableStateOf(AuthState())
    val login:State<AuthState> = _login


    //forgot Password
    private val _forgotPass = mutableStateOf(AuthState())
    val forgotPass:State<AuthState> = _forgotPass


    // google authentication
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    suspend fun signIn(email: String, displayName: String){
        delay(2000)
        //authRepository.getGoogleLoginAuth()
        _user.value = User(email, displayName)
    }

    fun registerUsers(){
        var error = if(nameState.value.isBlank()||emailState.value.isBlank()||phoneState.value.isBlank()||passwordState.value.isBlank()||confirmPasswordState.value.isBlank()){
            "Null Values"
        }  else if (phoneState.value.length < MIN_PHONE_LENGTH) {
            "Phone to short"
        } else if (phoneState.value.length > MIN_PHONE_LENGTH) {
            "Phone to long"
        }else if (passwordState.value.length<MIN_PASSWORD_LENGTH){
            "Password too short"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            "Email not valid"
        } else null

        error?.let {
            _register.value = AuthState(error = it)
            return
        }
        _register.value = AuthState(isLoading = true)
        viewModelScope.launch(Dispatchers.Main){
            val result = authRepository.registerUsers(nameState.value,phoneState.value,emailState.value,passwordState.value)
            when(result){
                is Resource.Success -> {
                    _register.value = AuthState(isLoading = false, isSuccessful = true)
                    Timber.d("Account Created Successfully")

                }
                is Resource.Error ->{
                    _register.value = AuthState(isLoading = false, error = result.message)
                    Timber.d(result.message)

                }
            }
        }
    }

    fun loginUser(){
        val error = if(emailState.value.isBlank()|| passwordState.value.isBlank()){
            "Null Value"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()){
            "Email not valid"
        } else null

        error?.let {
            _login.value = AuthState(error = it)
            return
        }
        _login.value = AuthState(isLoading = true)

        viewModelScope.launch(Dispatchers.Main){
            val result = authRepository.loginUser(emailState.value, passwordState.value)
            when(result){
                is Resource.Success -> {
                    _login.value = AuthState(isLoading = false, isSuccessful = true)
                    Timber.d("Reset in Successfully")

                }
                is Resource.Error ->{
                    _login.value = AuthState(isLoading = false, error = result.message)
                    Timber.d(result.message)

                }
            }
        }
    }

    fun forgotPassword(){
         if (emailState.value.isBlank()){
            "Null Value"
        } else{
            _forgotPass.value = AuthState(isLoading = true)
            val launch = viewModelScope.launch(Dispatchers.Main) {
                val result = authRepository.forgotPassword(emailState.value)
                when (result) {
                    is Resource.Success -> {
                        _forgotPass.value = AuthState(isLoading = false, isSuccessful = true)
                        Timber.d("Logged in Successfully")

                    }
                    is Resource.Error -> {
                        _forgotPass.value = AuthState(isLoading = false, error = result.message)
                        Timber.d(result.message)

                    }
                    else -> {}
                }
            }
            launch
        }
    }



}