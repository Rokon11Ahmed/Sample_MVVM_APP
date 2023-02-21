package com.example.wind.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wind.R
import com.example.wind.databinding.FragmentLoginBinding
import com.example.wind.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setUpViews() {
        super.setUpViews()

        initViews()
    }

    private fun initViews(){

        setClickListeners(binding.continueButton)
    }

    override fun onClick(view: View?) {
        super.onClick(view)

        view?.let {
            when (it.id) {
                R.id.continue_button -> {
                    hideKeyboard()
                    findNavController().navigate(R.id.action_login_fragment_to_send_fund_fragment)
                }
            }
        }
    }

}