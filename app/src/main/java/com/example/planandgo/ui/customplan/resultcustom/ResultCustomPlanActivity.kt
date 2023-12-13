package com.example.planandgo.ui.customplan.resultcustom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityResultCustomPlanBinding
import com.example.planandgo.ui.customplan.customtour.CustomTourActivity
import com.example.planandgo.ui.mydetailplan.MyDetailPlanActivity
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity

class ResultCustomPlanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultCustomPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultCustomPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnPlanIt.setOnClickListener {
            val intent = Intent(this, MyDetailPlanActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCustomPlan.setOnClickListener {
            val intent = Intent (this, CustomTourActivity::class.java)
            startActivity(intent)
            finish()
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val intent = Intent(this@ResultCustomPlanActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })


    }
}