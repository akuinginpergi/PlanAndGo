package com.example.planandgo.ui.customplan.customtour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planandgo.MainActivity
import com.example.planandgo.ViewModelFactory
import com.example.planandgo.data.ResultState
import com.example.planandgo.data.model.CreatePlanModel
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.databinding.ActivityCustomTourBinding
import com.example.planandgo.ui.customplan.customhotel.CustomHotelActivity

class CustomTourActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomTourBinding

    private val viewModel by viewModels<CustomTourViewModel>{
        ViewModelFactory.getInstance(this)
    }

    private lateinit var builder : AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTourBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Pilih Destinasi Wisata"

        var data = CreatePlanModel(
            asalKota = intent?.getStringExtra("ASAL_KOTA").toString(),
            kotaTujuan = intent?.getStringExtra("KOTA_TUJUAN").toString(),
            totalDana = intent?.getStringExtra("TOTAL_DANA").toString(),
            temaWisata = intent?.getStringExtra("TEMA_WISATA").toString(),
            waktuKeberangkatan = intent?.getStringExtra("WAKTU_KEBERANGKATAN").toString(),
            waktuKembali = intent?.getStringExtra("WAKTU_KEMBALI").toString(),
        )

        binding.fbCustomTour.setOnClickListener{
            val intent = Intent(this, CustomHotelActivity::class.java)
            startActivity(intent)
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                builder = AlertDialog.Builder(this@CustomTourActivity)
                builder.setTitle("Seluruh Hasil Custome Akan Dihapus!")
                    .setMessage("Apakah anda ingin membatalkan custom?")
                    .setCancelable(true)
                    .setPositiveButton("Iya"){ dialogInterface, it ->
                        val intent = Intent(this@CustomTourActivity, MainActivity::class.java)
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
        binding.rvCustomTour.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCustomTour.addItemDecoration(itemDecoration)

        getDestinationTour()

    }

    private fun setList(listDataItem : List<DataItem?>?){
        val adapter = ListDestinationTourAdapter()
        adapter.submitList(listDataItem)
        binding.rvCustomTour.adapter = adapter
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.ProgressBar.visibility = View.VISIBLE
        } else{
            binding.ProgressBar.visibility = View.INVISIBLE
        }
    }

    private fun getDestinationTour(){
        viewModel.getDestinationTour("Yogyakarta").observe(this){ result ->
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