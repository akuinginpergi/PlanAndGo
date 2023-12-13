package com.example.planandgo.ui.responseplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.MainActivity
import com.example.planandgo.databinding.ActivityPlanSuggestionBinding
import com.example.planandgo.ui.createplan.CreatePlanFragment
import com.example.planandgo.ui.customplan.customtour.CustomTourActivity
import com.example.planandgo.ui.home.HomeFragment
import com.example.planandgo.ui.mydetailplan.MyDetailPlanActivity

class PlanSuggestionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanSuggestionBinding

    private lateinit var builder : AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanSuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnPlanIt.setOnClickListener {
            val intent = Intent(this, MyDetailPlanActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCustomPlan.setOnClickListener {
            builder = AlertDialog.Builder(this@PlanSuggestionActivity)
            builder.setTitle("Seluruh Hasil Rekomendasi Akan Dihapus!")
                .setMessage("Ingin melanjutkan kustom rencana?")
                .setCancelable(true)
                .setPositiveButton("Iya"){dialogInterface, it ->
                    val intent = Intent(this, CustomTourActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak"){ dialogInterface, it ->
                    dialogInterface.cancel()
                }
            builder.show()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@PlanSuggestionActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }

}