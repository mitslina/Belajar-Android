package com.appstory.view.liststory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.appstory.repository.Repository
import com.appstory.data.network.respons.ListStoryItem

class ListStoryViewModel(repository: Repository) : ViewModel() {
    val story: LiveData<PagingData<ListStoryItem>> =
        repository.getStories().cachedIn(viewModelScope)
}