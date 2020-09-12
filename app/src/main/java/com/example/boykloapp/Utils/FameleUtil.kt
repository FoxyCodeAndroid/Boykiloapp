package com.example.boykloapp.Utils

import android.widget.ImageView
import com.foxycode.bedenolcer.R

object FameleUtil {

    var imgView2: ImageView?=null
    var bodyTypeSelectedItem2:Int?=null

    fun setImg2(genderSelectedItem2:Int) {
        if (genderSelectedItem2 == 0)
            selectedBodyTypeMale2()
        else if (genderSelectedItem2 == 1)
            selectedBodyTypeFamale2()
    }

    private fun selectedBodyTypeMale2() {
        if (bodyTypeSelectedItem2 == 0)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.erkek4beyaz)
                1 -> imgView2?.setImageResource(R.drawable.erkek5beyaz)
                2 -> imgView2?.setImageResource(R.drawable.erkek6beyaz)
                3 -> imgView2?.setImageResource(R.drawable.erkek7beyaz)
            }
        else if (bodyTypeSelectedItem2 == 1)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.erkek4esmer)
                1 -> imgView2?.setImageResource(R.drawable.erkek5esmer)
                2 -> imgView2?.setImageResource(R.drawable.erkek6esmer)
                3 -> imgView2?.setImageResource(R.drawable.erkek7esmer)
            }
        else if (bodyTypeSelectedItem2 == 2)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.erkek4zenci)
                1 -> imgView2?.setImageResource(R.drawable.erkek5zenci)
                2 -> imgView2?.setImageResource(R.drawable.erkek6zenci)
                3 -> imgView2?.setImageResource(R.drawable.erkek7zenci)
            }
        else if (bodyTypeSelectedItem2 == 3)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.erkek4sarisin)
                1 -> imgView2?.setImageResource(R.drawable.erkek5sarisin)
                2 -> imgView2?.setImageResource(R.drawable.erkek6sarisin)
                3 -> imgView2?.setImageResource(R.drawable.erkek7sarisin)
            }
    }

    private fun selectedBodyTypeFamale2() {
        if (bodyTypeSelectedItem2 == 0)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.kadin3beyaz)
                1 -> imgView2?.setImageResource(R.drawable.kadin4beyaz)
                2 -> imgView2?.setImageResource(R.drawable.kadin5beyaz)
                3 -> imgView2?.setImageResource(R.drawable.kadin6beyaz)
            }
        else if (bodyTypeSelectedItem2 == 1)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.kadin3esmer)
                1 -> imgView2?.setImageResource(R.drawable.kadin4esmer)
                2 -> imgView2?.setImageResource(R.drawable.kadin5esmer)
                3 -> imgView2?.setImageResource(R.drawable.kadin6esmer)
            }
        else if (bodyTypeSelectedItem2 == 2)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.kadin3zenci)
                1 -> imgView2?.setImageResource(R.drawable.kadin4zenci)
                2 -> imgView2?.setImageResource(R.drawable.kadin5zenci)
                3 -> imgView2?.setImageResource(R.drawable.kadin6zenci)
            }
        else if (bodyTypeSelectedItem2 == 3)
            when (MyUtils.endexLvl) {
                0 -> imgView2?.setImageResource(R.drawable.kadin3sarisin)
                1 -> imgView2?.setImageResource(R.drawable.kadin4sarisin)
                2 -> imgView2?.setImageResource(R.drawable.kadin5sarisin)
                3 -> imgView2?.setImageResource(R.drawable.kadin6sarisin)
            }
    }
}