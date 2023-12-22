package com.example.planandgo.data.response

import com.google.gson.annotations.SerializedName

data class ListTicketTransportation(

	@field:SerializedName("data")
	val data: List<DataTicketTransportationItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataTicketTransportationItem(

	@field:SerializedName("kota_keberangkatan")
	val kotaKeberangkatan: String? = null,

	@field:SerializedName("jenis_transportasi")
	val jenisTransportasi: String? = null,

	@field:SerializedName("kota_tiba")
	val kotaTiba: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("jarak")
	val jarak: String? = null,

	@field:SerializedName("nama_transportasi")
	val namaTransportasi: String? = null,

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("titik_keberangkatan")
	val titikKeberangkatan: String? = null
)
