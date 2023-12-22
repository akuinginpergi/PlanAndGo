package com.example.planandgo.ui.customplan.customtransportation.customticketdeparture

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
import com.example.planandgo.R
import com.example.planandgo.ViewModelFactory
import com.example.planandgo.data.ResultState
import com.example.planandgo.data.response.DataTicketTransportationItem
import com.example.planandgo.databinding.ActivityCustomTicketDepartureBinding
import com.example.planandgo.ui.customplan.customtransportation.customticketreturn.CustomTicketReturnActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomTicketDepartureActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomTicketDepartureBinding
    private lateinit var builder : AlertDialog.Builder


    private val viewModel by viewModels<CustomTicketDepartureViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTicketDepartureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Pilih Tiket Keberangkatan"

        binding.fbCustomDepartureTicket.setOnClickListener{
            val intent = Intent(this, CustomTicketReturnActivity::class.java)
            startActivity(intent)
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                builder = AlertDialog.Builder(this@CustomTicketDepartureActivity)
                builder.setTitle("Alert!")
                    .setMessage("Apakah anda ingin membatalkan custom?")
                    .setCancelable(true)
                    .setPositiveButton("Iya"){ dialogInterface, it ->
                        val intent = Intent(this@CustomTicketDepartureActivity, MainActivity::class.java)
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
        binding.rvCustomDepartureTicket.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCustomDepartureTicket.addItemDecoration(itemDecoration)

        getListTransportDeparture()

    }

    private fun setList(listTicketTransport: List<DataTicketTransportationItem?>?){
        val adapter = ListAdapterCustomTicketDeparture()
        adapter.submitList(listTicketTransport)
        binding.rvCustomDepartureTicket.adapter = adapter
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


    private fun getListTransportDeparture(){
        viewModel.getListTransportationDeparture("Yogyakarta", "DKI%20Jakarta").observe(this){
                result ->
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