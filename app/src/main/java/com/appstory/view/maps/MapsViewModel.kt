package com.appstory.view.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appstory.repository.Repository
import com.appstory.data.network.respons.ListStoryItem
import kotlinx.coroutines.launch

class MapsViewModel(private val repository: Repository) : ViewModel() {
    private val _isError = MutableLiveData<Boolean?>()
    val isError: LiveData<Boolean?> get() = _isError

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listStory = MutableLiveData<List<ListStoryItem>>()
    val listStory: LiveData<List<ListStoryItem>> get() = _listStory

    fun getStoriesWithLocation() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getStoriesWithLocation()
                _isError.value = response.error
                _message.value = response.message
                _listStory.value = response.listStory
            } catch (e: Exception) {
                _listStory.value = emptyList()
                _isError.value = true
                _message.value = "error. coba lagi!"
            } finally {
                _isLoading.value = false
            }
        }
    }
}