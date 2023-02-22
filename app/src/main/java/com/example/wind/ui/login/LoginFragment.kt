package com.example.wind.ui.login

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.wind.R
import com.example.wind.databinding.FragmentLoginBinding
import com.example.wind.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun setUpViews() {
        super.setUpViews()

        initViews()
        startObserver()
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
                    //findNavController().navigate(R.id.action_login_fragment_to_send_fund_fragment)
                    viewModel.login("nadimh", "1234")
                }
            }
        }
    }

    private fun startObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect {
                    isShowProgressBar(it.isLoading)

                    it.error.let {

                    }
                    it.data?.let {

                    }
                }
            }
        }
    }

    private fun isShowProgressBar(isShow: Boolean){

    }

}