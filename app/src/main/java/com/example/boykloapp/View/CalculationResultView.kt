package com.example.boykloapp.View

import com.example.boykloapp.data.AppMessage

interface CalculationResultView {
    fun onCalculationMessage(type: AppMessage)
}