package com.example.planandgo.ui.customplan.customhotel

import androidx.lifecycle.ViewModel
import com.example.planandgo.repository.Repository

class CustomHotelViewModel(private val repository: Repository): ViewModel() {

    fun getListHotel(kota_tujuan : String) = repository.getListHotel(kota_tujuan)

}