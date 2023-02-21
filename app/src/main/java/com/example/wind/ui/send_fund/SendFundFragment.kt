package com.example.wind.ui.send_fund

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wind.R
import com.example.wind.databinding.FragmentSendFundBinding
import com.example.wind.ui.base.BaseFragment

class SendFundFragment : BaseFragment<FragmentSendFundBinding>(FragmentSendFundBinding::inflate) {


    private val viewModel: SendFundViewModel by viewModels()

    override fun setUpViews() {
        super.setUpViews()

        initView()
    }

    private fun initView(){
        binding.userNameTextView.isSelected = true
        setClickListeners(binding.backButton)
    }

    override fun onClick(view: View?) {
        super.onClick(view)

        view?.let {
            when (it.id){
                R.id.back_button -> {
                    finishCurrent()
                }
            }
        }
    }


}