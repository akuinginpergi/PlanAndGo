package com.example.planandgo.ui.customplan.customtransportation.customticketreturn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityCustomTicketReturnBinding
import com.example.planandgo.ui.customplan.resultcustom.ResultCustomPlanActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomTicketReturnActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomTicketReturnBinding

    private lateinit var builder : AlertDialog.Builder

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
                        val intent = Intent(this@CustomTicketReturnActivity, PlanSuggestionActivity::class.java)
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