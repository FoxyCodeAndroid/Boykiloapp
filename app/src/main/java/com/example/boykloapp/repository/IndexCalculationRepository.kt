package com.example.boykloapp.repository
/*
    utkuglsvn
 */

//Presenter ile bu sınıf haberleşecek
class IndexCalculationRepository {

    internal fun calculationBodyCmKg(cm: Float, kg: Float) = (kg / ((cm * cm) / 10000))
    internal fun calculationBodyInchPound(inches: Float, pounds: Float) =
        ((pounds) / (inches * inches) * 703)
}