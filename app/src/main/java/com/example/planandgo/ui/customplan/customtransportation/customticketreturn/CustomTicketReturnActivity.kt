package com.example.planandgo.ui.customplan.customtransportation.customticketreturn

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
import com.example.planandgo.databinding.ActivityCustomTicketReturnBinding
import com.example.planandgo.ui.customplan.customtransportation.customticketdeparture.ListAdapterCustomTicketDeparture
import com.example.planandgo.ui.customplan.resultcustom.ResultCustomPlanActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomTicketReturnActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomTicketReturnBinding

    private lateinit var builder : AlertDialog.Builder

    private val viewModel by viewModels<CustomTicketReturnViewModel>{
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTicketReturnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pilih Tiket Pulang"

        binding.fbCustomReturnTicket.setOnClickListener {
            val intent = Intent(this, ResultCustomPlanActivity::class.java)
            startActivity(intent)
            PlanSuggestionActivity().finish()
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                builder = AlertDialog.Builder(this@CustomTicketReturnActivity)
                builder.setTitle("Alert!")
                    .setMessage("Apakah anda ingin membatalkan custom?")
                    .setCancelable(true)
                    .setPositiveButton("Iya"){ dialogInterface, it ->
                        val intent = Intent(this@CustomTicketReturnActivity, MainActivity::class.java)
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
        binding.rvCustomReturnTicket.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCustomReturnTicket.addItemDecoration(itemDecoration)

        getListTransportReturn()

    }


    private fun setList(listTicketTransport: List<DataTicketTransportationItem?>?){
        val adapter = ListAdapterCustomTicketDeparture()
        adapter.submitList(listTicketTransport)
        binding.rvCustomReturnTicket.adapter = adapter
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

    private fun getListTransportReturn(){
        viewModel.getListTicketTransportationReturn("DKI%20Jakarta", "Yogyakarta").observe(this){ result ->
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