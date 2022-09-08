package com.example.boykloapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.boykloapp.Utils.MaleUtil
import com.example.boykloapp.Utils.MyUtils
import com.foxycode.bedenolcer.R
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.tasks.Task
import kotlinx.android.synthetic.main.activity_couple.*


class CoupleActivity : AppCompatActivity() {

    private var genderList = listOf<String>()
    private var result1: Float = 0.0f
    private var result2: Float = 0.0f
    private var genderSelectedItem1: Int? = null
    private var genderSelectedItem2: Int? = null
    private var reviewManager: ReviewManager? = null
    private var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couple)

        setView()
    }

    private fun setView() {

        couple_result_text.movementMethod = ScrollingMovementMethod()

        genderList = resources.getStringArray(R.array.cinsiyet).toList()

        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet_couple.prompt = "Cinsiyet"
        listview_cinsiyet_couple?.adapter = genderAdapter

        listview_cinsiyet_couple.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem1 = position
                bundle.putString("Cinsiyet 1", listview_cinsiyet_couple.selectedItem.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@CoupleActivity, "Select Gender", Toast.LENGTH_SHORT).show()
            }
        }
        val genderAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet_couple2?.adapter = genderAdapter2

        listview_cinsiyet_couple2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem2 = position
                bundle.putString("Cinsiyet 2", listview_cinsiyet_couple2.selectedItem.toString())
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


    fun couple_btn_result(view: View) {
        pb_couple.currentProgress = 60

        if (checkEmpty(couple_edit_height.text.toString(), couple_edit_weight.text.toString())) {
            result1 = calBodyMass(
                couple_edit_height.text.toString().toFloat(),
                couple_edit_weight.text.toString().toFloat()
            )
            couple_result_text.text = MyUtils.resCal(result1, this)
            genderSelectedItem1?.let { MaleUtil.setImg(it) }
            couple_weight_text.text = "Weight :  ${couple_edit_height.text}"
            couple_height_text.text = "Height :  ${couple_edit_weight.text}"
            pb_couple.visibility = View.VISIBLE
            couple_weight_text.visibility = View.VISIBLE
            couple_height_text.visibility = View.VISIBLE
        } else
            MyUtils.showErrorToast(this)
//couple
        pb_couple2.currentProgress = 80
        if (checkEmpty(couple_edit_height2.text.toString(), couple_edit_weight2.text.toString())) {
            result1 = calBodyMass(
                couple_edit_height2.text.toString().toFloat(),
                couple_edit_weight2.text.toString().toFloat()
            )
            couple_result_text2.text = MyUtils.resCal(result1, this)
            genderSelectedItem1?.let { MaleUtil.setImg(it) }
            couple_weight_text2.text = "Weight :  ${couple_edit_height2.text}"
            couple_height_text2.text = "Height :  ${couple_edit_weight2.text}"
            pb_couple2.visibility = View.VISIBLE
            couple_weight_text2.visibility = View.VISIBLE
            couple_height_text2.visibility = View.VISIBLE
        } else
            MyUtils.showErrorToast(this)
        clear()
    }
    private fun clear(){
        couple_edit_age.text.clear()
        couple_edit_weight.text.clear()
        couple_edit_height.text.clear()
        couple_edit_age2.text.clear()
        couple_edit_weight2.text.clear()
        couple_edit_height2.text.clear()

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

