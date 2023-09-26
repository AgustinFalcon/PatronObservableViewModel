package com.example.patronobservable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.patronobservable.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.viewState.observe(this) {
            handleViewState(it)
        }


        binding.txtEmail.addTextChangedListener {
            viewModel.validateEmail(it.toString())
        }

        binding.txtPassword.addTextChangedListener {
            viewModel.validatePass(it.toString())
        }

        binding.txtRepeatPass.addTextChangedListener {
            viewModel.validateRepeatPass(it.toString())
        }

    }



    private fun handleViewState(viewState: RegisterStates) {
        when (viewState) {
            is RegisterStates.SuccessEmailField -> binding.layoutEmail.error = null

            is RegisterStates.ErrorEmailField -> binding.layoutEmail.error = viewState.email

            is RegisterStates.SuccessPassField -> binding.layoutPassword.error = null

            is RegisterStates.ErrorPassField -> binding.layoutPassword.error = "Mínimo: ${viewState.password.length}/3"

            is RegisterStates.SuccessRepeatPassField -> binding.layoutRepeatPass.error = null

            is RegisterStates.ErrorRepeatPassField -> binding.layoutRepeatPass.error = viewState.message

            is RegisterStates.BtnSuccess -> binding.btnRegister.isEnabled = true
            is RegisterStates.BtnError -> binding.btnRegister.isEnabled = false

        }
    }
}