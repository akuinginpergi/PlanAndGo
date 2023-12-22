package com.example.planandgo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.planandgo.di.Injection
import com.example.planandgo.repository.Repository
import com.example.planandgo.ui.authentication.login.LoginViewModel
import com.example.planandgo.ui.customplan.customhotel.CustomHotelViewModel
import com.example.planandgo.ui.customplan.customtour.CustomTourViewModel
import com.example.planandgo.ui.customplan.customtransportation.customticketdeparture.CustomTicketDepartureViewModel
import com.example.planandgo.ui.customplan.customtransportation.customticketreturn.CustomTicketReturnViewModel
import com.example.planandgo.ui.home.HomeViewModel
import com.example.planandgo.ui.profile.ProfileViewModel

class ViewModelFactory (private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CustomTourViewModel::class.java) -> {
                CustomTourViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CustomHotelViewModel::class.java)-> {
                CustomHotelViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CustomTicketDepartureViewModel::class.java) -> {
                CustomTicketDepartureViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CustomTicketReturnViewModel::class.java) -> {
                CustomTicketReturnViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}