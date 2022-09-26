package com.example.boykloapp.repository

import android.content.Context
import android.widget.Toast
import com.example.boykloapp.ui.SingleFragment
import com.example.boykloapp.utils.MyUtils
import com.foxycode.bedenolcer.R

/*
    utkuglsvn
 */

//Presenter ile bu sınıf haberleşecek
class IndexCalculationRepository {

    internal fun calculationBodyCmKg(cm: Float, kg: Float) = (kg / ((cm * cm) / 10000))

    internal fun calculationBodyInchPound(inches: Float, pounds: Float) = ((pounds) / (inches * inches) * 703)

    internal fun resCal(endex: Float,context: Context): String {
        if (endex > 0 && endex <= 18.5) {
            MyUtils.endexLvl = 0
            return context.resources.getString(R.string.ince)
        } else if (endex > 18.5 && endex <= 24.9) {
            MyUtils.endexLvl = 1
            return context.resources.getString(R.string.fit)
        } else if (endex > 24.9 && endex <= 29.9) {
            MyUtils.endexLvl = 2
            return context.resources.getString(R.string.r_kilo)
        } else if (endex > 29.9) {
            MyUtils.endexLvl = 3
            return context.resources.getString(R.string.asiri_kilo)
        }
        return "Değerlerde problem oldu, tekrar giriniz :)"
    }


    internal fun showErrorToast(context: Context) {
        context.toast()
    }
    fun Context.toast() =  Toast.makeText(this, resources.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
}