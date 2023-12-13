package com.example.planandgo.ui.customplan.customhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.TtsSpan.DigitsBuilder
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityCustomHotelBinding
import com.example.planandgo.ui.customplan.customtransportation.customticketdeparture.CustomTicketDepartureActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class CustomHotelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCustomHotelBinding
    private lateinit var builder : AlertDialog.Builder
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
    }
}