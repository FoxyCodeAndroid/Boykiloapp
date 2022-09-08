package com.example.boykloapp.Utils

import android.content.Context
import android.widget.Toast
import com.foxycode.bedenolcer.R


object MyUtils {

    var endexLvl: Int? = null

    fun Context.toast() =  Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()

    fun showErrorToast(context: Context) {
        context.toast()
    }

    fun resCal(endex: Float,context: Context): String {
        if (endex > 0 && endex <= 18.5) {
            endexLvl = 0
            return context.resources.getString(R.string.ince)
        } else if (endex > 18.5 && endex <= 24.9) {
            endexLvl = 1
            return context.resources.getString(R.string.fit)
        } else if (endex > 24.9 && endex <= 29.9) {
            endexLvl = 2
            return context.resources.getString(R.string.r_kilo)
        } else if (endex > 29.9) {
            endexLvl = 3
            return context.resources.getString(R.string.asiri_kilo)
        }
        return "Değerlerde problem oldu, tekrar giriniz :)"
    }

}