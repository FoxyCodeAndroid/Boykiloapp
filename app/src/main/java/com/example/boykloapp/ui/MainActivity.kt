package com.example.boykloapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.foxycode.bedenolcer.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var genderList = listOf<String>()
    var skinColorList = listOf<String>()
    var result1: Float = 0.0f
    var result2: Float = 0.0f
    var genderSelectedItem1: Int? = null
    var genderSelectedItem2: Int? = null
    var bodyTypeSelectedItem1: Int? = null
    var bodyTypeSelectedItem2: Int? = null
    var endexLvl: Int? = null
    var bundle = Bundle()
    lateinit var adView : AdView

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        adView = findViewById(R.id.adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
        setView()
    }

    fun setView() {
        genderList = resources.getStringArray(R.array.cinsiyet).toList()
        skinColorList = resources.getStringArray(R.array.ten).toList()

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet.prompt = "Cinsiyet"
        listview_cinsiyet?.setAdapter(genderAdapter)

        listview_cinsiyet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem1 = position
                bundle.putString("Cinsiyet 1", listview_cinsiyet.selectedItem.toString());

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val skinColorAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, skinColorList)
        listview_ten_rengi.prompt = "Ten Rengi"
        listview_ten_rengi?.setAdapter(skinColorAdapter)

        listview_ten_rengi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                bodyTypeSelectedItem1 = position
                bundle.putString("Ten Reng, 1", listview_ten_rengi.selectedItem.toString());
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val genderAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet2.prompt = "Cinsiyet"
        listview_cinsiyet2?.setAdapter(genderAdapter2)

        listview_cinsiyet2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem2 = position
                bundle.putString("Cinsiyet 2", listview_cinsiyet2.selectedItem.toString());
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val skinColorAdapter2 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, skinColorList)
        listview_ten_rengi2.prompt = "Ten Rengi"
        listview_ten_rengi2?.setAdapter(skinColorAdapter2)

        listview_ten_rengi2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                bodyTypeSelectedItem2 = position
                bundle.putString("Ten Rengi 2", listview_ten_rengi2.selectedItem.toString());
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }

    fun calBodyMass(cm: Int, weight: Float): Float {
        bundle.putInt("Boy", cm);
        bundle.putFloat("Agırlık", weight);
        return weight / ((cm * cm)/10000)
    }

    fun checkEmpty(lentghEt: String?, weightEt: String?): Boolean {
        if (TextUtils.isEmpty(lentghEt))
            return false
        if (TextUtils.isEmpty(weightEt))
            return false
        return true
    }

    fun showToast() {
        Toast.makeText(this, "Değerleri Giriniz", Toast.LENGTH_SHORT).show()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCal -> {
                if (checkEmpty(etxt_boy.text.toString(), etxt_kilo.text.toString())) {
                    result1 = calBodyMass(
                        etxt_boy.text.toString().toInt(),
                        etxt_kilo.text.toString().toFloat()
                    )
                    txtView_result1.text = resCal(result1)
                    setImg()
                } else
                    showToast()
                if (checkEmpty(etxt_boy2.text.toString(), etxt_kilo2.text.toString())) {
                    result2 = calBodyMass(
                        etxt_boy2.text.toString().toInt(),
                        etxt_kilo2.text.toString().toFloat()
                    )
                    resCal(result2)
                    txtView_result2.text = resCal(result2)
                    setImg2()
                    mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                } else
                    showToast()

            }
        }

    }

    fun resCal(endex: Float): String {
        if (endex > 0 && endex <= 18.4) {
            endexLvl = 0
            return resources.getString(R.string.ince)
        } else if (endex > 18.4 && endex <= 24.9) {
            endexLvl = 1
            return resources.getString(R.string.fit)
        } else if (endex > 25 && endex <= 39.9) {
            endexLvl = 2
            return resources.getString(R.string.r_kilo)
        } else if (endex > 40) {
            endexLvl = 3
            return resources.getString(R.string.asiri_kilo)
        }
        return "Değerlerde problem oldu, tekrar giriniz :)"
    }

    fun setImg() {
        if (genderSelectedItem1 == 0)
            selectedBodyTypeMale()
        else if (genderSelectedItem1 == 1)
            selectedBodyTypeFamale()
    }

    fun setImg2() {
        if (genderSelectedItem2 == 0)
            selectedBodyTypeMale2()
        else if (genderSelectedItem2 == 1)
            selectedBodyTypeFamale2()
    }

    private fun selectedBodyTypeMale2() {
        if (bodyTypeSelectedItem2 == 0)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.erkek4beyaz)
                1 -> imgView2.setImageResource(R.drawable.erkek5beyaz)
                2 -> imgView2.setImageResource(R.drawable.erkek6beyaz)
                3 -> imgView2.setImageResource(R.drawable.erkek7beyaz)
            }
        else if (bodyTypeSelectedItem2 == 1)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.erkek4esmer)
                1 -> imgView2.setImageResource(R.drawable.erkek5esmer)
                2 -> imgView2.setImageResource(R.drawable.erkek6esmer)
                3 -> imgView2.setImageResource(R.drawable.erkek7esmer)
            }
        else if (bodyTypeSelectedItem2 == 2)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.erkek4zenci)
                1 -> imgView2.setImageResource(R.drawable.erkek5zenci)
                2 -> imgView2.setImageResource(R.drawable.erkek6zenci)
                3 -> imgView2.setImageResource(R.drawable.erkek7zenci)
            }
        else if (bodyTypeSelectedItem2 == 3)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.erkek4sarisin)
                1 -> imgView2.setImageResource(R.drawable.erkek5sarisin)
                2 -> imgView2.setImageResource(R.drawable.erkek6sarisin)
                3 -> imgView2.setImageResource(R.drawable.erkek7sarisin)
            }
    }

    private fun selectedBodyTypeFamale2() {
        if (bodyTypeSelectedItem2 == 0)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.kadin3beyaz)
                1 -> imgView2.setImageResource(R.drawable.kadin4beyaz)
                2 -> imgView2.setImageResource(R.drawable.kadin5beyaz)
                3 -> imgView2.setImageResource(R.drawable.kadin6beyaz)
            }
        else if (bodyTypeSelectedItem2 == 1)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.kadin3esmer)
                1 -> imgView2.setImageResource(R.drawable.kadin4esmer)
                2 -> imgView2.setImageResource(R.drawable.kadin5esmer)
                3 -> imgView2.setImageResource(R.drawable.kadin6esmer)
            }
        else if (bodyTypeSelectedItem2 == 2)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.kadin3zenci)
                1 -> imgView2.setImageResource(R.drawable.kadin4zenci)
                2 -> imgView2.setImageResource(R.drawable.kadin5zenci)
                3 -> imgView2.setImageResource(R.drawable.kadin6zenci)
            }
        else if (bodyTypeSelectedItem2 == 3)
            when (endexLvl) {
                0 -> imgView2.setImageResource(R.drawable.kadin3sarisin)
                1 -> imgView2.setImageResource(R.drawable.kadin4sarisin)
                2 -> imgView2.setImageResource(R.drawable.kadin5sarisin)
                3 -> imgView2.setImageResource(R.drawable.kadin6sarisin)
            }
    }

    fun selectedBodyTypeMale() {
        if (bodyTypeSelectedItem1 == 0)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.erkek4beyaz)
                1 -> imgView1.setImageResource(R.drawable.erkek5beyaz)
                2 -> imgView1.setImageResource(R.drawable.erkek6beyaz)
                3 -> imgView1.setImageResource(R.drawable.erkek7beyaz)
            }
        else if (bodyTypeSelectedItem1 == 1)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.erkek4esmer)
                1 -> imgView1.setImageResource(R.drawable.erkek5esmer)
                2 -> imgView1.setImageResource(R.drawable.erkek6esmer)
                3 -> imgView1.setImageResource(R.drawable.erkek7esmer)
            }
        else if (bodyTypeSelectedItem1 == 2)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.erkek4zenci)
                1 -> imgView1.setImageResource(R.drawable.erkek5zenci)
                2 -> imgView1.setImageResource(R.drawable.erkek6zenci)
                3 -> imgView1.setImageResource(R.drawable.erkek7zenci)
            }
        else if (bodyTypeSelectedItem1 == 3)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.erkek4sarisin)
                1 -> imgView1.setImageResource(R.drawable.erkek5sarisin)
                2 -> imgView1.setImageResource(R.drawable.erkek6sarisin)
                3 -> imgView1.setImageResource(R.drawable.erkek7sarisin)
            }

    }

    fun selectedBodyTypeFamale() {
        if (bodyTypeSelectedItem1 == 0)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.kadin3beyaz)
                1 -> imgView1.setImageResource(R.drawable.kadin4beyaz)
                2 -> imgView1.setImageResource(R.drawable.kadin5beyaz)
                3 -> imgView1.setImageResource(R.drawable.kadin6beyaz)
            }
        else if (bodyTypeSelectedItem1 == 1)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.kadin3esmer)
                1 -> imgView1.setImageResource(R.drawable.kadin4esmer)
                2 -> imgView1.setImageResource(R.drawable.kadin5esmer)
                3 -> imgView1.setImageResource(R.drawable.kadin6esmer)
            }
        else if (bodyTypeSelectedItem1 == 2)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.kadin3zenci)
                1 -> imgView1.setImageResource(R.drawable.kadin4zenci)
                2 -> imgView1.setImageResource(R.drawable.kadin5zenci)
                3 -> imgView1.setImageResource(R.drawable.kadin6zenci)
            }
        else if (bodyTypeSelectedItem1 == 3)
            when (endexLvl) {
                0 -> imgView1.setImageResource(R.drawable.kadin3sarisin)
                1 -> imgView1.setImageResource(R.drawable.kadin4sarisin)
                2 -> imgView1.setImageResource(R.drawable.kadin5sarisin)
                3 -> imgView1.setImageResource(R.drawable.kadin6sarisin)
            }
    }
}

