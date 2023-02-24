package com.example.wind.ui.send_fund

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.wind.R
import com.example.wind.databinding.FragmentSendFundBinding
import com.example.wind.ui.base.BaseFragment
import com.example.wind.utils.IntentKey
import com.example.wind.utils.hideKeyboard
import com.example.wind.utils.loadCircularImage

class SendFundFragment : BaseFragment<FragmentSendFundBinding>(FragmentSendFundBinding::inflate),
    TextWatcher {


    //private val viewModel: SendFundViewModel by viewModels()
    private var userId: String = ""
    private var userName: String = ""
    private var userWallet: String = ""
    private var userEmail: String = ""
    private var userImageUrl: String = ""
    private var userBalance: String = ""
    private var userCurrency: String = ""

    override fun setUpViews() {
        super.setUpViews()
        Log.d("TAG", "startObserver: SendFundFragment setUpViews")
        userId = arguments?.getString(IntentKey.KEY_USER_ID) as String
        userName = arguments?.getString(IntentKey.KEY_USER_NAME) as String
        userWallet = arguments?.getString(IntentKey.KEY_USER_WALLET) as String
        userEmail = arguments?.getString(IntentKey.KEY_USER_EMAIL) as String
        userImageUrl = arguments?.getString(IntentKey.KEY_USER_IMAGE_URL) as String
        userBalance = arguments?.getString(IntentKey.KEY_BALANCE) as String
        userCurrency = arguments?.getString(IntentKey.KEY_CURRENCY) as String

        initView()
    }

    private fun initView() {
        binding.apply {
            if (userImageUrl.isNotEmpty()) {
                userImageView.loadCircularImage(userImageUrl)
            }
            amountCurrencyTextView.text = userCurrency
            userNameTextView.text = userName.plus(" - ").plus(userWallet)
            maxBalanceTextView.text = getString(R.string.IDS_BALANCE).plus(" ")
                .plus(getString(R.string.IDS_USDC).plus(" ").plus(userBalance))
            userNameTextView.isSelected = true
            setClickListeners(backButton, maxButton, continueButton)
            amountEditText.addTextChangedListener(this@SendFundFragment)
            isShowAddFund(false)
        }
    }

    override fun onClick(view: View?) {
        super.onClick(view)

        view?.let {
            when (it.id) {
                R.id.back_button -> {
                    finishCurrent()
                }
                R.id.max_button -> {
                    binding.amountEditText.setText(userBalance)
                    binding.amountEditText.setSelection(binding.amountEditText.text!!.length)
                }
                R.id.continue_button -> {
                    getActivity()?.hideKeyboard()
                }
                else -> {}
            }
        }
    }

    private fun isShowAddFund(isShow: Boolean) {
        binding.insufficientBalanceTextView.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.addFundButton.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun beforeTextChanged(
        chatSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {

    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable?) {
        if (editable.toString().isNotEmpty()){
            processAmountInput(editable.toString())
        }
    }

    private fun processAmountInput(amount: String) {
        if (amount.toDouble() > userBalance.toDouble()) {
            isShowAddFund(true)
        } else {
            isShowAddFund(false)
        }
    }


}