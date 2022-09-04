package com.example.boykloapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.boykloapp.utils.FameleUtil
import com.example.boykloapp.utils.MaleUtil
import com.example.boykloapp.utils.MyUtils
import com.foxycode.bedenolcer.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var genderList = listOf<String>()
    private var skinColorList = listOf<String>()
    private var result1: Float = 0.0f
    private var result2: Float = 0.0f
    private var genderSelectedItem1: Int? = null
    private var genderSelectedItem2: Int? = null
    private var bundle = Bundle()
    private var reviewManager: ReviewManager? = null
    lateinit var mAdView: AdView

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        reviewManager = ReviewManagerFactory.create(this)
        showRateApp()
        setView()
    }

    private fun setView() {
        txtView_result1.movementMethod = ScrollingMovementMethod()
        txtView_result2.movementMethod = ScrollingMovementMethod()

        MaleUtil.imgView1 = imgView1
        FameleUtil.imgView2 = imgView2

        genderList = resources.getStringArray(R.array.cinsiyet).toList()
        skinColorList = resources.getStringArray(R.array.ten).toList()

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet.prompt = "Cinsiyet"
        listview_cinsiyet?.adapter = genderAdapter

        listview_cinsiyet.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem1 = position
                bundle.putString("Cinsiyet 1", listview_cinsiyet.selectedItem.toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val skinColorAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, skinColorList)
        listview_ten_rengi?.adapter = skinColorAdapter

        listview_ten_rengi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                MaleUtil.bodyTypeSelectedItem1 = position
                bundle.putString("Ten Reng, 1", listview_ten_rengi.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val genderAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet2?.adapter = genderAdapter2

        listview_cinsiyet2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem2 = position
                bundle.putString("Cinsiyet 2", listview_cinsiyet2.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

        val skinColorAdapter2 =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, skinColorList)
        listview_ten_rengi2.prompt = "Ten Rengi"
        listview_ten_rengi2?.adapter = skinColorAdapter2

        listview_ten_rengi2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                FameleUtil.bodyTypeSelectedItem2 = position
                bundle.putString("Ten Rengi 2", listview_ten_rengi2.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }

    private fun calBodyMass(cm: Float, weight: Float): Float {
        bundle.putFloat("Boy", cm)
        bundle.putFloat("Agırlık", weight)
        return weight / ((cm * cm) / 10000)
    }

    private fun checkEmpty(lentghEt: String?, weightEt: String?): Boolean {
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
                    txtView_result1.text = MyUtils.resCal(result1, this)
                    genderSelectedItem1?.let { MaleUtil.setImg(it) }
                } else
                    MyUtils.showErrorToast(this)
                if (checkEmpty(etxt_boy2.text.toString(), etxt_kilo2.text.toString())) {
                    result2 = calBodyMass(
                        etxt_boy2.text.toString().toFloat(),
                        etxt_kilo2.text.toString().toFloat()
                    )
                    txtView_result2.text = MyUtils.resCal(result2, this)
                    genderSelectedItem2?.let { FameleUtil.setImg2(it) }
                    mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
                } else
                    MyUtils.showErrorToast(this)
            }
        }

    }

    fun showRateApp() {
        val request: Task<ReviewInfo> = reviewManager!!.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo: ReviewInfo = task.result
                val flow: Task<Void> = reviewManager!!.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { }
            } else {
                //Hata vs icin
            }
        }
    }
}

