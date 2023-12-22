package com.example.planandgo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.planandgo.data.pref.UserModel
import com.example.planandgo.repository.Repository

class MainViewModel (private val repository: Repository) : ViewModel() {
    fun getSession() : LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }
}