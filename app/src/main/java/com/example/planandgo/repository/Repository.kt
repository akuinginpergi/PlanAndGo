package com.example.planandgo.repository

import androidx.lifecycle.liveData
import com.example.planandgo.data.ResultState
import com.example.planandgo.data.pref.UserModel
import com.example.planandgo.data.pref.UserPreferences
import com.example.planandgo.data.response.DestinationTourResponse
import com.example.planandgo.data.response.ListHotelResponse
import com.example.planandgo.data.response.ListTicketTransportation
import com.example.planandgo.data.retrofit.ApiConfig
import com.example.planandgo.data.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class Repository private constructor(
    private val userPreferences: UserPreferences,
    private val apiService: ApiService
){
    suspend fun saveSession(user: UserModel){
        userPreferences.saveSession(user)
    }

    fun getSession() : Flow<UserModel>{
        return userPreferences.getSession()
    }

    suspend fun logout(){
        userPreferences.logout()
    }

    fun getDestinationTor(kota_tujuan : String) = liveData<ResultState<DestinationTourResponse>> {
        emit(ResultState.Loading)
        try {
            val successGetDestinationTour = apiService.getDestinationTour(kota_tujuan)
            emit(ResultState.Success(successGetDestinationTour))
        } catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DestinationTourResponse::class.java)
            emit(ResultState.Error(errorResponse.message.toString()))
        }
    }

    fun getListHotel(kota_tujuan: String) = liveData<ResultState<ListHotelResponse>> {
        emit(ResultState.Loading)
        try {
            val successGetListHotel = apiService.getListHotel(kota_tujuan)
            emit(ResultState.Success(successGetListHotel))
        } catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ListHotelResponse::class.java)
            emit(ResultState.Error(errorResponse.message.toString()))

        }
    }

    fun getListTransportationDeparture(kota_tujuan: String, kota_asal: String) = liveData<ResultState<ListTicketTransportation>> {
        emit(ResultState.Loading)
        try {
            val successGetListHotel = apiService.getListTransportationDeparture(kota_tujuan, kota_asal)
            emit(ResultState.Success(successGetListHotel))
        } catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ListTicketTransportation::class.java)
            emit(ResultState.Error(errorResponse.message.toString()))
        }
    }

    fun getListTransportationReturn(kota_tujuan: String, kota_asal: String) = liveData<ResultState<ListTicketTransportation>> {
        emit(ResultState.Loading)
        try {
            val successGetListHotel = apiService.getListTransportationReturn(kota_tujuan, kota_asal)
            emit(ResultState.Success(successGetListHotel))
        } catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ListTicketTransportation::class.java)
            emit(ResultState.Error(errorResponse.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(
            userPreferences: UserPreferences,
            apiService: ApiService
        ) : Repository =
            instance ?: synchronized(this){
                instance ?: Repository(userPreferences, apiService)
            }.also { instance = it }
    }
}