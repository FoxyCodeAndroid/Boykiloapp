package com.example.boykloapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.boykloapp.Utils.FameleUtil
import com.example.boykloapp.Utils.MaleUtil
import com.example.boykloapp.Utils.MyUtils
import com.foxycode.bedenolcer.R
import com.google.android.gms.ads.AdRequest
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var genderList = listOf<String>()
    var skinColorList = listOf<String>()
    var result1: Float = 0.0f
    var result2: Float = 0.0f
    var genderSelectedItem1: Int? = null
    var genderSelectedItem2: Int? = null
    var bundle = Bundle()

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        setView()
    }

    fun setView() {
        txtView_result1.movementMethod = ScrollingMovementMethod()
        txtView_result2.movementMethod = ScrollingMovementMethod()

        MaleUtil.imgView1=imgView1
        FameleUtil.imgView2=imgView2

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
        listview_ten_rengi?.setAdapter(skinColorAdapter)

        listview_ten_rengi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                MaleUtil.bodyTypeSelectedItem1 = position
                bundle.putString("Ten Reng, 1", listview_ten_rengi.selectedItem.toString());
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val genderAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
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
                FameleUtil.bodyTypeSelectedItem2 = position
                bundle.putString("Ten Rengi 2", listview_ten_rengi2.selectedItem.toString());
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }

    fun calBodyMass(cm:Float, weight: Float): Float {
        bundle.putFloat("Boy", cm);
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


    fun onClick(view: View) {
        when (view.id) {
            R.id.btnCal -> {
                if (checkEmpty(etxt_boy.text.toString(), etxt_kilo.text.toString())) {
                    result1 = calBodyMass(
                        etxt_boy.text.toString().toFloat(),
                        etxt_kilo.text.toString().toFloat()
                    )
                    txtView_result1.text = MyUtils.resCal(result1,this)
                    genderSelectedItem1?.let { MaleUtil.setImg(it) }
                } else
                    MyUtils.showErrorToast(this)
                if (checkEmpty(etxt_boy2.text.toString(), etxt_kilo2.text.toString())) {
                    result2 = calBodyMass(
                        etxt_boy2.text.toString().toFloat(),
                        etxt_kilo2.text.toString().toFloat()
                    )
                    txtView_result2.text = MyUtils.resCal(result2,this)
                    genderSelectedItem2?.let { FameleUtil.setImg2(it) }
                    mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                } else
                    MyUtils.showErrorToast(this)
            }
        }

    }

}

