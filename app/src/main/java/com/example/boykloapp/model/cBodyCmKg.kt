package com.example.boykloapp.model

import android.text.TextUtils

class cBodyCmKg(override val height : Float, override val weight : Float):BodyCmKgInterface {
    override fun isDataValid(): Int {
        return if (height.toInt().toString().isEmpty())
            0
        else if (height.toInt() <= 1)
            1
        else if (weight.toInt() <= 1)
            2
        else if (weight.toInt().toString().isEmpty())
            3
        else
            -1
    }
}

