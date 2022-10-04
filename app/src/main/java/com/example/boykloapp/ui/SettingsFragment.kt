package com.example.boykloapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.boykloapp.utils.SettingsUtils
import com.foxycode.bedenolcer.R
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.fragment_single.*

class SettingsFragment : PreferenceFragmentCompat(),SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.main_gray2))
    }
    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
       if (p1=="mass_type"){
           val prefs = p0?.getString(p1,"1")
           when(prefs?.toInt()){
               1 -> {
                   SettingsUtils.chosenSettings = true
                   val sharedPreferences = this.activity?.getSharedPreferences("com.foxycode.bedenolcer",
                       Context.MODE_PRIVATE)
                   sharedPreferences!!.edit().putBoolean("pref",SettingsUtils.chosenSettings!!).apply()
               }
               2->{
                   SettingsUtils.chosenSettings = false
                   val sharedPreferences = this.activity?.getSharedPreferences("com.foxycode.bedenolcer",
                       Context.MODE_PRIVATE)
                   sharedPreferences!!.edit().putBoolean("pref",SettingsUtils.chosenSettings!!).apply()
                   }
           }
       }
    }
}