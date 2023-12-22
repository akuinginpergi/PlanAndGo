package com.example.planandgo.ui.customplan.customtransportation.customticketdeparture

import androidx.lifecycle.ViewModel
import com.example.planandgo.repository.Repository

class CustomTicketDepartureViewModel (private val repository: Repository): ViewModel() {

    fun getListTransportationDeparture(kotaTujuan: String, kotaAsal: String) = repository.getListTransportationDeparture(kotaTujuan, kotaAsal)

}