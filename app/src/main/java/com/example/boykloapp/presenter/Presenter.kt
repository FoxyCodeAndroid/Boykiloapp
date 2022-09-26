package com.example.boykloapp.presenter

import android.content.Context

interface Presenter {
    fun calculationBodyCmKg(cm: Float, kg: Float)= (kg / ((cm * cm) / 10000))
    fun resCal(endex: Int,context: Context) : String
    fun calculationBodyInchPound(inches: Float, pounds: Float) = ((pounds) / (inches * inches) * 703)
}