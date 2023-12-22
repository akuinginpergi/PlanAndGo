package com.example.planandgo.ui.customplan.customtour

import androidx.lifecycle.ViewModel
import com.example.planandgo.repository.Repository

class CustomTourViewModel (private val repository: Repository) : ViewModel() {

    fun getDestinationTour(kotaTujuan : String) = repository.getDestinationTor(kotaTujuan)
}