package com.appstory.view.detailstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appstory.repository.Repository
import com.appstory.data.network.respons.ErrorResponse
import com.appstory.data.network.respons.StoryResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailStoryViewModel(private val repository: Repository) : ViewModel() {

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _detailStory = MutableLiveData<StoryResponse?>()
    val detailStory: LiveData<StoryResponse?> get() = _detailStory

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetailStory(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getDetailStory(id)
                _detailStory.value = response.story
                _message.value = response.message
            } catch (e: Exception) {
                if (e is HttpException) {
                    _detailStory.value = null
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                    val errorMessage = errorBody.message
                    _message.value = errorMessage
                } else {
                    _detailStory.value = null
                    _message.value = "error. coba lagi!"
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}