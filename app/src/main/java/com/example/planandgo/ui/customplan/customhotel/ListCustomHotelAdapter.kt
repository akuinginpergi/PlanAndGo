package com.example.planandgo.ui.customplan.customhotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.planandgo.R
import com.example.planandgo.data.response.DataHotelItem
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.databinding.ListHotelItemBinding
import com.example.planandgo.databinding.ListTourItemBinding
import com.example.planandgo.ui.customplan.customtour.ListDestinationTourAdapter

class ListCustomHotelAdapter : ListAdapter<DataHotelItem, ListCustomHotelAdapter.MyViewHolder>(DIFF_CALLBACK){
    class MyViewHolder(val binding : ListHotelItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(hotel : DataHotelItem){
            binding.tvnameHotel.text = hotel.namaHotel.toString()
            binding.tvLevelStarHotel.text = hotel.bintang.toString()
            binding.tvPriceTicketHotel.text = "Rp. ${ hotel.harga.toString() } / Malam "

            Glide.with(itemView)
                .load(hotel.gambar)
                .error(R.drawable.perambanan)
                .into(binding.imageViewHotel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListHotelItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        holder.binding.btnChooseHotel.setOnClickListener {
            holder.binding.btnChooseHotel.setBackgroundColor(R.color.green.toInt())
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataHotelItem>(){
            override fun areItemsTheSame(oldItem: DataHotelItem, newItem: DataHotelItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataHotelItem, newItem: DataHotelItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}