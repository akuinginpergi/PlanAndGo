package com.example.planandgo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planandgo.repository.Repository

class HomeViewModel (private val repository: Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getDestinationTour(kotaTujuan : String) = repository.getDestinationTor(kotaTujuan)


}