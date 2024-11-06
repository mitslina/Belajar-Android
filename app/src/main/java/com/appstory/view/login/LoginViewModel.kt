package com.appstory.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appstory.repository.Repository
import com.appstory.data.network.respons.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _isError = MutableLiveData<Boolean?>()
    val isError: LiveData<Boolean?> get() = _isError

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                _isError.value = response.error
                _message.value = response.message
            } catch (e: Exception) {
                if (e is HttpException) {
                    _isError.value = true
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                    val errorMessage = errorBody.message
                    _message.value = errorMessage
                } else {
                    _isError.value = true
                    _message.value = "error. coba lagi!"
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}