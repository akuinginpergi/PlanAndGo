package com.example.planandgo.ui.customplan.customtour

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.green
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.planandgo.R
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.databinding.ListTourItemBinding

class ListDestinationTourAdapter : ListAdapter<DataItem, ListDestinationTourAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding : ListTourItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tour : DataItem){
            binding.tvnameDestinationTour.text = tour.namaWisata.toString()
            binding.tvRatingDestinationTour.text = tour.rating.toString()
            binding.tvPriceTicketTour.text = "Rp. ${ tour.harga.toString() }"

            Glide.with(itemView)
                .load(tour.gambar)
                .error(R.drawable.perambanan)
                .into(binding.imageViewTour)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListTourItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        holder.binding.btnChooseTour.setOnClickListener {
            holder.binding.btnChooseTour.setBackgroundColor(R.color.green.toInt())
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}