package com.example.boykloapp.model



class resCalUser(override val endex : Int):resCalUserInterface {
    override fun isDataValid(): Int {
        return if (endex > 0 && endex <= 18.5)
            0
        else if (endex > 18.5 && endex <= 24.9)
             1
        else if (endex > 24.9 && endex <= 29.9)
            2
        else if (endex > 29.9)
            3
        else
            -1
    }
}