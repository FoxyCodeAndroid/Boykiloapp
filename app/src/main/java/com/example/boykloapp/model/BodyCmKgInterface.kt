package com.example.boykloapp.model

import android.content.Context

interface BodyCmKgInterface {
    val height : Float
    val weight : Float
    fun isDataValid() : Int
}