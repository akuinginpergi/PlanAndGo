package com.example.planandgo.ui.customplan.customhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.TtsSpan.DigitsBuilder
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.ViewModelFactory
import com.example.planandgo.data.ResultState
import com.example.planandgo.data.response.DataHotelItem
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.databinding.ActivityCustomHotelBinding
import com.example.planandgo.ui.customplan.customtour.ListDestinationTourAdapter
import com.example.planandgo.ui.customplan.customtransportation.customticketdeparture.CustomTicketDepartureActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomHotelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomHotelBinding
    private lateinit var builder : AlertDialog.Builder

    private val viewModel by viewModels<CustomHotelViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Pilih Tiket Hotel"

        binding.fbCustomHotel.setOnClickListener{
            val intent = Intent(this, CustomTicketDepartureActivity::class.java)
            startActivity(intent)
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                builder = AlertDialog.Builder(this@CustomHotelActivity)
                builder.setTitle("Seluruh Hasil Kustom Akan Dihapus!")
                    .setMessage("Apakah anda ingin membatalkan kustom?")
                    .setCancelable(true)
                    .setPositiveButton("Iya"){ dialogInterface, it ->
                        val intent = Intent(this@CustomHotelActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("Tidak"){ dialogInterface, it ->
                        dialogInterface.cancel()
                    }
                builder.show()
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.rvCustomHotel.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCustomHotel.addItemDecoration(itemDecoration)

        getListHotel()

    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.ProgressBar.visibility = View.VISIBLE
        } else{
            binding.ProgressBar.visibility = View.INVISIBLE
        }
    }
    private fun setList(listDataItem : List<DataHotelItem?>?){
        val adapter = ListCustomHotelAdapter()
        adapter.submitList(listDataItem)
        binding.rvCustomHotel.adapter = adapter
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun getListHotel(){
        viewModel.getListHotel("Yogyakarta").observe(this){ result ->
            if(result != null){
                when(result){
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        setList(result.data.data)
                        showLoading(false)
                    }
                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }

        }
    }





}