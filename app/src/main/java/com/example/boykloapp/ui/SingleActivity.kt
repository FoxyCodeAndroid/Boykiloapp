package com.example.boykloapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.boykloapp.Utils.MaleUtil
import com.example.boykloapp.Utils.MyUtils
import com.foxycode.bedenolcer.R
import kotlinx.android.synthetic.main.activity_single.*

class SingleActivity : AppCompatActivity() {

    private var bundle = Bundle()
    private var genderList = listOf<String>()
    private var result1: Float = 0.0f
    private var genderSelectedItem1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        setView()
    }
    private fun setView() {
        //single_result_text.movementMethod = ScrollingMovementMethod()

        genderList = resources.getStringArray(R.array.cinsiyet).toList()

       val genderAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet_single.prompt = "Cinsiyet"
        listview_cinsiyet_single?.adapter = genderAdapter

        listview_cinsiyet_single.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem1 = position
                bundle.putString("Cinsiyet 1", listview_cinsiyet_single.selectedItem.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@SingleActivity, "Select Gender", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkEmpty(lentghEt: String?, weightEt: String?): Boolean {
        if (TextUtils.isEmpty(lentghEt))
            return false
        if (TextUtils.isEmpty(weightEt))
            return false
        return true
    }
    private fun calBodyMass(cm: Float, weight: Float): Float {
        bundle.putFloat("Boy", cm)
        bundle.putFloat("Agırlık", weight)
        return weight / ((cm * cm) / 10000)
    }
    fun btn_result(view :View){

        pb.currentProgress = 130
        if (checkEmpty(single_edit_height.text.toString(), single_edit_weight.text.toString())) {
                result1 = calBodyMass(
                    single_edit_height.text.toString().toFloat(),
                    single_edit_weight.text.toString().toFloat()
                )
            single_result_text.text = MyUtils.resCal(result1, this)
            genderSelectedItem1?.let { MaleUtil.setImg(it) }
            single_weight_text.text = "Weight :  ${single_edit_weight.text}"
            single_height_text.text = "Height :  ${single_edit_height.text}"
            single_age_text.text =    "Age :  ${ single_edit_age.text }"
            pb.visibility = View.VISIBLE
            single_age_text.visibility = View.VISIBLE
            single_weight_text.visibility = View.VISIBLE
            single_height_text.visibility = View.VISIBLE
            single_gender_text.visibility = View.VISIBLE
            } else
                MyUtils.showErrorToast(this)
        clear()

    }
    private fun clear(){
        single_edit_age.text.clear()
        single_edit_weight.text.clear()
        single_edit_height.text.clear()
    }

    fun toolbar_back(view :View){
        val back_intent = Intent(this@SingleActivity,MainScreenActivity::class.java)
        startActivity(back_intent)
    }
    fun toolbar_close(view :View){
        val back_intent = Intent(this@SingleActivity,MainScreenActivity::class.java)
        startActivity(back_intent)
    }
}