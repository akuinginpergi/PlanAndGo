package com.example.planandgo.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.planandgo.R
import com.example.planandgo.databinding.ActivityWelcomeBinding
import com.example.planandgo.ui.authentication.login.LoginActivity

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnRegister -> showToast("Halaman ini masih dalam proses pengembangan :(")
            R.id.btnLogin ->  startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}