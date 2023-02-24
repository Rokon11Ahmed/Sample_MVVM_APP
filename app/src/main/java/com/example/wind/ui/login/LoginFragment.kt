package com.example.wind.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.model.UserInfo
import com.example.wind.R
import com.example.wind.databinding.FragmentLoginBinding
import com.example.wind.ui.base.BaseFragment
import com.example.wind.utils.IntentKey
import com.example.wind.utils.hideKeyboard
import com.example.wind.utils.showShortToast
import com.mukesh.OnOtpCompletionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), OnOtpCompletionListener, TextWatcher {

    private val viewModel: LoginViewModel by viewModels()
    var userPin = ""
    override val isDataLoadDisable: Boolean
        get() = true

    override fun setUpViews() {
        super.setUpViews()

        initViews()
        startObserver()
    }

    private fun initViews(){
        binding.apply {
            isContinueButtonEnable(isEnable = false)
            setClickListeners(continueButton)
            otpView.setOtpCompletionListener(this@LoginFragment)
            isOtpEnable(false)
            userNameEditText.addTextChangedListener(this@LoginFragment)
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)

        view?.let {
            when (it.id) {
                R.id.continue_button -> {
                    getActivity()?.hideKeyboard()
                    val userName = binding.userNameEditText.text?.trim().toString()
                    if (isUserNameValid(userName)){
                        viewModel.login(userName, userPin)
                    }
                }
            }
        }
    }

    private fun startObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect {
                    isShowProgressBar(it.isLoading)

                    it.error.apply {
                        getActivity()?.showShortToast(this)
                    }
                    it.data?.let {
                        val userData = it as UserInfo
                        if (userData.errorMessage.isNullOrBlank()){
                            val bundle = Bundle()
                            bundle.putString(IntentKey.KEY_USER_ID, userData.id)
                            bundle.putString(IntentKey.KEY_USER_NAME, userData.userName)
                            bundle.putString(IntentKey.KEY_USER_WALLET, userData.walletAddress)
                            bundle.putString(IntentKey.KEY_USER_EMAIL, userData.email)
                            bundle.putString(IntentKey.KEY_USER_IMAGE_URL, userData.profileImageUrl)
                            bundle.putString(IntentKey.KEY_BALANCE, userData.balance.toString())
                            bundle.putString(IntentKey.KEY_CURRENCY, userData.currency)
                            Log.d("TAG", "startObserver: LoginFragment")
                            findNavController().navigate( R.id.action_login_fragment_to_send_fund_fragment, bundle)
                        }else{
                            getActivity()?.showShortToast(userData.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun isShowProgressBar(isShow: Boolean){
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun onOtpCompleted(otp: String?) {
        otp?.let { userPin = it }
        getActivity()?.hideKeyboard()
        isContinueButtonEnable(isEnable = true)
    }

    override fun beforeTextChanged(chatSequence: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable?) {
        isOtpEnable(isUserNameValid(editable.toString()))
    }

    private fun isOtpEnable(isEnable: Boolean){
        if (isEnable){
            binding.otpView.isEnabled = true
            binding.otpView.alpha = 1f
        }else{
            binding.otpView.isEnabled = false
            binding.otpView.alpha = 0.7f
        }
    }

    private fun isContinueButtonEnable(isEnable: Boolean){
        if (isEnable){
            binding.continueButton.isEnabled = true
            binding.continueButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_button_active_bg)
        }else{
            binding.continueButton.isEnabled = false
            binding.continueButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_button_inactive_bg)
        }
    }

}