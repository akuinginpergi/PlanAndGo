package com.example.planandgo.ui.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.planandgo.MainActivity
import com.example.planandgo.R
import com.example.planandgo.ViewModelFactory
import com.example.planandgo.data.pref.UserModel
import com.example.planandgo.databinding.ActivityLoginBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.emailAddress.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                setMyEmail(p0)
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.butnLogin.setOnClickListener {
            if (binding.emailAddress.text.isNullOrEmpty() or binding.password.text.isNullOrEmpty()){
                showToast("Pastikan Email dan Password sudah terisi")
            } else{
                postLogin()
            }
        }

    }

    private fun setMyEmail(result: CharSequence){
        if(result.isNotEmpty()){
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
            if (result.matches(emailRegex.toRegex())){
                binding.emailAddress.error = null
            } else {
                binding.emailAddress.error = "Masukan Email dengan benar!"
            }
        } else {
            binding.emailAddress.error = null
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        binding.ProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun postLogin() {
        val email = binding.emailAddress.text.toString()
        viewModel.saveSession(UserModel(email, "sample_token"))
        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("Anda berhasil login! Selamat datang di aplikasi Plan&Go")
            setPositiveButton("Lanjut"){_, _, ->
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }
}