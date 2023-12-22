package com.example.planandgo.data.retrofit

import com.example.planandgo.data.response.DestinationTourResponse
import com.example.planandgo.data.response.ListHotelResponse
import com.example.planandgo.data.response.ListTicketTransportation
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("choose-destination")
    suspend fun getDestinationTour(
        @Query("kota_tujuan") kota_tujuan: String
    ) : DestinationTourResponse

    @GET("choose-hotel")
    suspend fun getListHotel(
        @Query("kota_tujuan") kota_tujuan: String
    ) : ListHotelResponse

    @GET("choose-depart")
    suspend fun getListTransportationDeparture(
        @Query("kota_tujuan") kota_tujuan: String,
        @Query("kota_asal") kota_asal: String
    ) : ListTicketTransportation

    @GET("choose-return")
    suspend fun getListTransportationReturn(
        @Query("kota_tujuan") kota_tujuan: String,
        @Query("kota_asal") kota_asal: String
    ) : ListTicketTransportation



}