package com.example.boykloapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foxycode.bedenolcer.R
import com.foxycode.bedenolcer.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}