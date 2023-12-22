package com.example.planandgo.di

import android.content.Context
import com.example.planandgo.data.pref.UserPreferences
import com.example.planandgo.data.pref.dataStore
import com.example.planandgo.data.retrofit.ApiConfig
import com.example.planandgo.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(pref, apiService)
    }
}