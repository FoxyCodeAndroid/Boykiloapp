package com.example.boykloapp.presenter

import android.content.Context
import com.example.boykloapp.View.CalculationResultView
import com.example.boykloapp.data.AppMessage
import com.example.boykloapp.model.cBodyCmKg
import com.example.boykloapp.model.cBodyInch
import com.example.boykloapp.model.resCalUser
import com.foxycode.bedenolcer.R


class CalculationPresenter(private var calculationResultView: CalculationResultView) :
    CalculationViewState {
    override fun calculationBodyCmKg(cm: Float, kg: Float): Float {
        val cBodyCmKg = cBodyCmKg(cm, kg)
        calculationMessage(cBodyCmKg.isDataValid())
        return kg / ((cm * cm) / 10000)
    }

    override fun resCal(
        endex: Int,
        context: Context
    //appmessage farklı veri type ekleyip control et
    ): String { // presenter da context kullanmak doğru olmaz
        val user2 = resCalUser(endex)
        return when (user2.isDataValid()) {
            0 -> context.resources.getString(R.string.ince)
            1 -> context.resources.getString(R.string.fit)
            2 -> context.resources.getString(R.string.r_kilo)
            3 -> context.resources.getString(R.string.asiri_kilo)
            else -> "Değerlerde problem oldu, tekrar giriniz :)" //localization da problem
        }
    }

    override fun calculationBodyInchPound(inches: Float, pounds: Float): Float {
        val user = cBodyInch(inches, pounds)
        calculationMessage(user.isDataValid())
        return ((pounds) / (inches * inches) * 703)
    }

    private fun calculationMessage(type: Int) {
        when (type) {
            0, 3 -> calculationResultView.onCalculationMessage(AppMessage.NO_EMPTY)
            1, 2 -> calculationResultView.onCalculationMessage(AppMessage.NO_ZERO)
            else -> calculationResultView.onCalculationMessage(AppMessage.SUCCESS_CALCULATION)
        }
    }

}