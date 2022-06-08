package com.odhiambodevelopers.easyeats.screens.user

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odhiambodevelopers.easyeats.data.repository.MainRepository
import com.odhiambodevelopers.easyeats.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val mainRepository: MainRepository) :ViewModel() {

    private val _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri

    private val _imageUrl = mutableStateOf("")
    val imageUrl: State<String> = _imageUrl

    private val _isLoading = mutableStateOf(false)
    val isLoading:State<Boolean> = _isLoading

    fun setImageUri(imageUri: Uri){
        _imageUri.value = imageUri
    }
    fun uploadImage(imageUrl:Uri){
        viewModelScope.launch {
            when(val res = mainRepository.uploadImage(imageUrl)){
                is Resource.Success->{
                    _isLoading.value = false
                    if (res.data?.isSuccessful == true){
                        _imageUrl.value = res.data.result.toString()
                    }
                }
                is Resource.Error ->{
                    _isLoading.value = false
                    _imageUrl.value = res.message.toString()
                }
            }
        }

    }
}