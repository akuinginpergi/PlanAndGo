package com.example.planandgo.ui.customplan.customtransportation.customticketreturn

import androidx.lifecycle.ViewModel
import com.example.planandgo.repository.Repository

class CustomTicketReturnViewModel(private val repository: Repository):ViewModel() {

    fun getListTicketTransportationReturn(kotaTujuan: String, kotaAsal: String) = repository.getListTransportationReturn(kotaTujuan, kotaAsal)

}