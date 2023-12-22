package com.example.planandgo.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planandgo.repository.Repository
import kotlinx.coroutines.launch

class ProfileViewModel (private val repository: Repository) : ViewModel() {

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }
}