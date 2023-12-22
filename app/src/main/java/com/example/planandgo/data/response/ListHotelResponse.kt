package com.example.planandgo.data.response

import com.google.gson.annotations.SerializedName

data class ListHotelResponse(

	@field:SerializedName("data")
	val data: List<DataHotelItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataHotelItem(

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("bintang")
	val bintang: String? = null,

	@field:SerializedName("jenis_harga")
	val jenisHarga: String? = null,

	@field:SerializedName("nama_hotel")
	val namaHotel: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
