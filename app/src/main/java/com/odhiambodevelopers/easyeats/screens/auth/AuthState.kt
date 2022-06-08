package com.odhiambodevelopers.easyeats.screens.auth

data class AuthState(
    val isLoading:Boolean = false,
    val isSuccessful:Boolean = false,
    val error:String? = null
)
