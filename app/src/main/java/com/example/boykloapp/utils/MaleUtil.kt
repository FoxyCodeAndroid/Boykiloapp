package com.example.boykloapp.utils

import android.widget.ImageView
import com.foxycode.bedenolcer.R

object MaleUtil{

    var imgView1: ImageView?=null
    var bodyTypeSelectedItem1:Int?=null


    fun setImg(genderSelectedItem1:Int) {
        if (genderSelectedItem1 == 0)
            selectedBodyTypeMale()
        else if (genderSelectedItem1 == 1)
            selectedBodyTypeFamale()
    }

    fun selectedBodyTypeMale() {
        if (bodyTypeSelectedItem1 == 0)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.erkek4beyaz)
                1 -> imgView1?.setImageResource(R.drawable.erkek5beyaz)
                2 -> imgView1?.setImageResource(R.drawable.erkek6beyaz)
                3 -> imgView1?.setImageResource(R.drawable.erkek7beyaz)
            }
        else if (bodyTypeSelectedItem1 == 1)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.erkek4esmer)
                1 -> imgView1?.setImageResource(R.drawable.erkek5esmer)
                2 -> imgView1?.setImageResource(R.drawable.erkek6esmer)
                3 -> imgView1?.setImageResource(R.drawable.erkek7esmer)
            }
        else if (bodyTypeSelectedItem1 == 2)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.erkek4zenci)
                1 -> imgView1?.setImageResource(R.drawable.erkek5zenci)
                2 -> imgView1?.setImageResource(R.drawable.erkek6zenci)
                3 -> imgView1?.setImageResource(R.drawable.erkek7zenci)
            }
        else if (bodyTypeSelectedItem1 == 3)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.erkek4sarisin)
                1 -> imgView1?.setImageResource(R.drawable.erkek5sarisin)
                2 -> imgView1?.setImageResource(R.drawable.erkek6sarisin)
                3 -> imgView1?.setImageResource(R.drawable.erkek7sarisin)
            }

    }

    fun selectedBodyTypeFamale() {
        if (bodyTypeSelectedItem1 == 0)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.kadin3beyaz)
                1 -> imgView1?.setImageResource(R.drawable.kadin4beyaz)
                2 -> imgView1?.setImageResource(R.drawable.kadin5beyaz)
                3 -> imgView1?.setImageResource(R.drawable.kadin6beyaz)
            }
        else if (bodyTypeSelectedItem1 == 1)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.kadin3esmer)
                1 -> imgView1?.setImageResource(R.drawable.kadin4esmer)
                2 -> imgView1?.setImageResource(R.drawable.kadin5esmer)
                3 -> imgView1?.setImageResource(R.drawable.kadin6esmer)
            }
        else if (bodyTypeSelectedItem1 == 2)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.kadin3zenci)
                1 -> imgView1?.setImageResource(R.drawable.kadin4zenci)
                2 -> imgView1?.setImageResource(R.drawable.kadin5zenci)
                3 -> imgView1?.setImageResource(R.drawable.kadin6zenci)
            }
        else if (bodyTypeSelectedItem1 == 3)
            when (MyUtils.endexLvl) {
                0 -> imgView1?.setImageResource(R.drawable.kadin3sarisin)
                1 -> imgView1?.setImageResource(R.drawable.kadin4sarisin)
                2 -> imgView1?.setImageResource(R.drawable.kadin5sarisin)
                3 -> imgView1?.setImageResource(R.drawable.kadin6sarisin)
            }
    }

}