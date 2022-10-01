package com.example.boykloapp.Utils

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

object ToastUtil {
    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toasty.error(context, message, duration).show()
    }
}