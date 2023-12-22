package com.example.planandgo.ui.customplan.customtransportation.customticketdeparture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.planandgo.R
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.data.response.DataTicketTransportationItem
import com.example.planandgo.databinding.ListTourItemBinding
import com.example.planandgo.databinding.ListTransportationItemBinding

class ListAdapterCustomTicketDeparture : ListAdapter<DataTicketTransportationItem, ListAdapterCustomTicketDeparture.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val binding : ListTransportationItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(transportation : DataTicketTransportationItem){
            binding.ticketNameDestination.text = "${transportation.namaTransportasi} - ${transportation.kelas} - TANGGAL"
            binding.timeAndRouteTicket.text = "${transportation.titikKeberangkatan} - ${transportation.kotaTiba}"
            binding.priceTicketTransport.text = "Rp.${transportation.harga} / Kursi"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListTransportationItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        holder.binding.button.setOnClickListener {
            holder.binding.button.setBackgroundColor(R.color.green.toInt())
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTicketTransportationItem>(){
            override fun areItemsTheSame(oldItem: DataTicketTransportationItem, newItem: DataTicketTransportationItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataTicketTransportationItem, newItem: DataTicketTransportationItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}