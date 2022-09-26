package com.example.boykloapp.View

interface CalculationResultView {
    fun onCalculationSuccess(message : String)
    fun onCalculationError(message : String)
}