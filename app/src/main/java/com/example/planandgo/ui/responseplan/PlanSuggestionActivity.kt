package com.example.planandgo.ui.responseplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.planandgo.MainActivity
import com.example.planandgo.databinding.ActivityPlanSuggestionBinding
import com.example.planandgo.ui.createplan.CreatePlanFragment
import com.example.planandgo.ui.home.HomeFragment
import com.example.planandgo.ui.mydetailplan.MyDetailPlanActivity

class PlanSuggestionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanSuggestionBinding
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

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}