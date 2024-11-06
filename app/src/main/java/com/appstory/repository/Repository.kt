package com.appstory.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.appstory.data.StoryRemoteMediator
import com.appstory.data.network.api.ApiService
import com.appstory.data.database.StoriesRoomDatabase
import com.appstory.data.pref.UserModel
import com.appstory.data.pref.UserPreference
import com.appstory.data.network.respons.AddStoryResponse
import com.appstory.data.network.respons.ListStoryItem
import com.appstory.data.network.respons.LoginResponse
import com.appstory.data.network.respons.RegisterResponse
import com.appstory.data.network.respons.StoriesResponse
import com.appstory.data.network.respons.StoryDetailResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class Repository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val database: StoriesRoomDatabase
) {

    private suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        val response = apiService.register(name, email, password)
        return response
    }

    suspend fun login(userId: String, password: String): LoginResponse {
        val response = apiService.login(userId, password)
        if (response.error == false) {
            val user = UserModel(
                userId = response.loginResult?.userId ?: "",
                name = response.loginResult?.name?: "",
                token = response.loginResult?.token ?: "token_story_dicoding",
                isLogin = true
            )
            saveSession(user)
        }
        return response
    }

    suspend fun getDetailStory(id: String): StoryDetailResponse {
        val response = apiService.getStoryDetail(id)
        return response
    }

    suspend fun addNewStory(photo: File, description: String, lat: Double?, lon: Double?): AddStoryResponse {
        val requestImageFile = photo.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            photo.name,
            requestImageFile
        )
        val descRequestBody = description.toRequestBody("text/plain".toMediaType())
        val response = apiService.addNewStory(multipartBody, descRequestBody, lat, lon)
        return response
    }

    suspend fun getStoriesWithLocation(): StoriesResponse {
        val response = apiService.getStoriesWithLocation()
        return response
    }

    fun getStories(): LiveData<PagingData<ListStoryItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(database, apiService),
            pagingSourceFactory = {
                database.storyDao().getAllStory()
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(preference: UserPreference, apiService: ApiService, database: StoriesRoomDatabase) = Repository(preference, apiService, database)
    }
}