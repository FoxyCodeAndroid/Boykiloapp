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
        val code =  cBodyCmKg.isDataValid()
        if (code == 0)
            calculationResultView.onCalculationError("Boş olamaz")
        else if (code ==1)
            calculationResultView.onCalculationError("0 olamaz")
        else if (code ==2)
            calculationResultView.onCalculationError("0 olamaz")
        else if (code ==3)
            calculationResultView.onCalculationError("boş olamaz")
        else
            calculationResultView.onCalculationSuccess("Ölçüm Başarılı")
        return kg / ((cm * cm) / 10000)
    }
    override fun resCal(endex: Int, context: Context) : String {
        val user2 = resCalUser(endex)
        val code =  user2.isDataValid()
        if (code == 0)
            return  context.resources.getString(R.string.ince)
        else if (code ==1)
            return  context.resources.getString(R.string.fit)
        else if (code ==2)
            return  context.resources.getString(R.string.r_kilo)
        else if (code ==3)
            return  context.resources.getString(R.string.asiri_kilo)
        else
            return  "Değerlerde problem oldu, tekrar giriniz :)"
    }
    override fun calculationBodyInchPound(inches: Float, pounds: Float): Float {
        val user = cBodyInch(inches,pounds)
        val code =  user.isDataValid()
        if (code == 0)
            calculationResultView.onCalculationError("Boş olamaz")
        else if (code ==1)
            calculationResultView.onCalculationError("0 olamaz")
        else if (code ==2)
            calculationResultView.onCalculationError("0 olamaz")
        else if (code ==3)
            calculationResultView.onCalculationError("boş olamaz")
        else
            calculationResultView.onCalculationSuccess("Ölçüm Başarılı")
        return ((pounds) / (inches * inches) * 703)
    }


}