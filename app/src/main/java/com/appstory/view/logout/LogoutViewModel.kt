package com.appstory.view.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appstory.data.pref.UserModel
import com.appstory.repository.Repository
import kotlinx.coroutines.launch

class LogoutViewModel(private val repository: Repository) : ViewModel() {

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val _name = MutableLiveData<String?>()
    val name: LiveData<String?> get() = _name

    init {
        viewModelScope.launch {
            repository.getSession().collect { user ->
                _name.value = user.name
            }
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}