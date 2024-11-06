package com.appstory.di

import android.content.Context
import com.appstory.data.database.StoriesRoomDatabase
import com.appstory.data.network.api.ApiConfig
import com.appstory.data.pref.UserPreference
import com.appstory.data.pref.dataStore
import com.appstory.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val database = StoriesRoomDatabase.getDatabase(context)
        return Repository.getInstance(pref, apiService, database)
    }
}