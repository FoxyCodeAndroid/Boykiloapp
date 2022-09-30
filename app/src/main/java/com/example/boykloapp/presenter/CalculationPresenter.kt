package com.example.boykloapp.presenter

import android.content.Context
import com.example.boykloapp.View.CalculationResultView
import com.example.boykloapp.model.cBodyCmKg
import com.example.boykloapp.model.cBodyInch
import com.example.boykloapp.model.resCalUser
import com.foxycode.bedenolcer.R


class CalculationPresenter(private var calculationResultView : CalculationResultView):Presenter {
    override fun calculationBodyCmKg(cm: Float, kg: Float): Float {
        val cBodyCmKg = cBodyCmKg(cm, kg)
        when (cBodyCmKg.isDataValid()) {//use it when -> cleaning
            0 -> calculationResultView.onCalculationError("Boş olamaz")
            1 -> calculationResultView.onCalculationError("0 olamaz")
            2 -> calculationResultView.onCalculationError("0 olamaz")
            3 -> calculationResultView.onCalculationError("boş olamaz")
            else -> calculationResultView.onCalculationSuccess("Ölçüm Başarılı")
        }
        return kg / ((cm * cm) / 10000)
    }
    override fun resCal(endex: Int, context: Context) : String { // presenter da context kullanmak doğru olmaz
        val user2 = resCalUser(endex)
        return when (user2.isDataValid()) { // clean
            0 -> context.resources.getString(R.string.ince)
            1 -> context.resources.getString(R.string.fit)
            2 -> context.resources.getString(R.string.r_kilo)
            3 -> context.resources.getString(R.string.asiri_kilo)
            else -> "Değerlerde problem oldu, tekrar giriniz :)" //localization da problem
        }
    }
    override fun calculationBodyInchPound(inches: Float, pounds: Float): Float {
        val user = cBodyInch(inches,pounds)
        when (user.isDataValid()) {
            0 -> calculationResultView.onCalculationError("Boş olamaz")
            1 -> calculationResultView.onCalculationError("0 olamaz")
            2 -> calculationResultView.onCalculationError("0 olamaz")
            3 -> calculationResultView.onCalculationError("boş olamaz")
            //0 ve 3 aynı cevapsa , kulan atıyorum 0,3 -> ...
            //1 ,2 -> ...
            else -> calculationResultView.onCalculationSuccess("Ölçüm Başarılı")
        }
        return ((pounds) / (inches * inches) * 703)
    }


}