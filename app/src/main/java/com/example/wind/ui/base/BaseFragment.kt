package com.example.wind.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.wind.ui.MainActivity
import java.lang.ref.WeakReference

abstract class BaseFragment<VBinding : ViewDataBinding>(
    private val bindingFactory: (LayoutInflater) -> VBinding
) :
    Fragment(), View.OnClickListener {

    protected val binding: VBinding by lazy { bindingFactory(layoutInflater) }
    open val isDataLoadDisable: Boolean = false
    private var isDataLoaded = false
    lateinit var activity: WeakReference<MainActivity>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            activity = WeakReference(context)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processOnSetUpView()
    }

    private fun processOnSetUpView() {
        if (!isDataLoadDisable) {
            setUpViews()
        } else {
            if (!isDataLoaded) {
                isDataLoaded = true
                setUpViews()
            } else {
                backStackAction()
            }
        }
    }

    open fun setUpViews() {}

    open fun backStackAction() {}

    protected fun setClickListeners(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
    }

    open fun showKeyboard() {
        activity.get()?.let {
            val imm: InputMethodManager =
                it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    protected fun finishCurrent() {
        activity.get()?.apply {
            if (onBackPressedDispatcher.hasEnabledCallbacks()) {
                onBackPressedDispatcher.onBackPressed()
            } else {
                onBackPressed()
            }
        }
    }

    fun isUserNameValid(name: String): Boolean {
        var charCount = 0
        var previousSpecialChar: Char = Char.MIN_VALUE
        var isConsecutiveSpecialChar = false
        name.forEach {
            if (it == '.' || it == '_') {
                if (it == previousSpecialChar) {
                    isConsecutiveSpecialChar = true
                } else {
                    previousSpecialChar = it
                }
            } else {
                charCount++
                previousSpecialChar = Char.MIN_VALUE
            }
        }
        if (name.length > 33 || charCount < 3 || isConsecutiveSpecialChar) {
            return false
        }
        return true
    }
}