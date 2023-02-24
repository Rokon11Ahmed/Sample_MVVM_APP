package com.example.wind.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideKeyboard() {
    this.apply {
        val imm: InputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}

fun Activity.showShortToast(message: String?){
    this.apply {
        if (!message.isNullOrEmpty()){
            Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}