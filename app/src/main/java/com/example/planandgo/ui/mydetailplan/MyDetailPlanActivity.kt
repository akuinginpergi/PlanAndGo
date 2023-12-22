package com.example.planandgo.ui.mydetailplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityMyDetailPlanBinding

class MyDetailPlanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMyDetailPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDetailPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}