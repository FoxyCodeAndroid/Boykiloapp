package com.example.boykloapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.foxycode.bedenolcer.R

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }
    fun single(view : View){
        val intent = Intent(this@MainScreenActivity,SingleActivity::class.java)
        startActivity(intent)
    }
    fun couple(view : View){
        val intent = Intent(this@MainScreenActivity,CoupleActivity::class.java)
        startActivity(intent)
    }
    fun aboutus(view : View){
        //intent
    }
}