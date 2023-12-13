package com.example.planandgo.ui.customplan.customtransportation.customticketdeparture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityCustomTicketDepartureBinding
import com.example.planandgo.ui.customplan.customtransportation.customticketreturn.CustomTicketReturnActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomTicketDepartureActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomTicketDepartureBinding
    private lateinit var builder : AlertDialog.Builder
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


    }
}