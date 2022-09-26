package com.example.boykloapp.model

import android.text.TextUtils

class cBodyInch(override val inches : Float,override val pounds : Float):BodyInchInterface {
    override fun isDataValid(): Int {
        return if (TextUtils.equals(inches.toString(),""))
            0
        else if (inches.toInt() <= 1)
            1
        else if (pounds.toInt() <= 1)
            2
        else if (TextUtils.equals(pounds.toString(),""))
            3
        else
            -1
    }
}