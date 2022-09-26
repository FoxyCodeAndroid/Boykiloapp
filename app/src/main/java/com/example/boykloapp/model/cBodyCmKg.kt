package com.example.boykloapp.model

import android.text.TextUtils

class cBodyCmKg(override val height : Float, override val weight : Float):BodyCmKgInterface {
    override fun isDataValid(): Int {
        return if (TextUtils.equals(height.toString(),""))
            0
        else if (height.toInt() <= 1)
            1
        else if (weight.toInt() <= 1)
            2
        else if (TextUtils.equals(weight.toString(),""))
            3
        else
            -1
    }
}

