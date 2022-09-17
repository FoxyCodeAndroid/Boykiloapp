package com.example.boykloapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.boykloapp.Utils.MaleUtil
import com.example.boykloapp.Utils.MyUtils
import com.foxycode.bedenolcer.R
import com.foxycode.bedenolcer.databinding.FragmentCoupleBinding
import com.google.android.play.core.review.ReviewManager
import kotlinx.android.synthetic.main.fragment_couple.*

class CoupleFragment : Fragment() {
    private var genderList = listOf<String>()
    private var result1: Float = 0.0f
    private var result2: Float = 0.0f
    private var genderSelectedItem1: Int? = null
    private var genderSelectedItem2: Int? = null
    private var reviewManager: ReviewManager? = null
    private var bundle = Bundle()

    private var _binding: FragmentCoupleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoupleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
        super.onViewCreated(view, savedInstanceState)

        btn_couple_result.setOnClickListener {
            pb_couple.currentProgress = 60

            if (checkEmpty(
                    binding.coupleEditHeight.text.toString(),
                    binding.coupleWeightText.text.toString()
                )
            ) {
                result1 = calBodyMass(
                    binding.coupleEditHeight.text.toString().toFloat(),
                    binding.coupleWeightText.text.toString().toFloat()
                )
                binding.coupleResultText.text = MyUtils.resCal(result1, requireContext())
                genderSelectedItem1?.let { MaleUtil.setImg(it) }
                binding.coupleWeightText.text = "Weight :  ${binding.coupleEditHeight.text}"
                binding.coupleEditHeight.text = "Height :  ${binding.coupleWeightText.text}"
                binding.pbCouple.visibility = View.VISIBLE
                binding.coupleWeightText.visibility = View.VISIBLE
                binding.coupleHeightText.visibility = View.VISIBLE
            } else
                MyUtils.showErrorToast(requireContext())
//couple
            pb_couple2.currentProgress = 80
            if (checkEmpty(
                    couple_edit_height2.text.toString(),
                    couple_edit_weight2.text.toString()
                )
            ) {
                result1 = calBodyMass(
                    couple_edit_height2.text.toString().toFloat(),
                    couple_edit_weight2.text.toString().toFloat()
                )
                couple_result_text2.text = MyUtils.resCal(result1, requireContext())
                genderSelectedItem1?.let { MaleUtil.setImg(it) }
                couple_weight_text2.text = "Weight :  ${couple_edit_height2.text}"
                couple_height_text2.text = "Height :  ${couple_edit_weight2.text}"
                pb_couple2.visibility = View.VISIBLE
                couple_weight_text2.visibility = View.VISIBLE
                couple_height_text2.visibility = View.VISIBLE
            } else
                MyUtils.showErrorToast(requireContext())
            clear()
        }
    }

    private fun clear() {
        couple_edit_age.text.clear()
        couple_edit_weight.text.clear()
        couple_edit_height.text.clear()
        couple_edit_age2.text.clear()
        couple_edit_weight2.text.clear()
        couple_edit_height2.text.clear()

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

    private fun setView() {

        couple_result_text.movementMethod = ScrollingMovementMethod()

        genderList = resources.getStringArray(R.array.cinsiyet).toList()

        val genderAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet_couple.prompt = "Cinsiyet"
        listview_cinsiyet_couple?.adapter = genderAdapter

        listview_cinsiyet_couple.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
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
                    Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show()
                }
            }
        val genderAdapter2 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderList)
        listview_cinsiyet_couple2?.adapter = genderAdapter2

        listview_cinsiyet_couple2.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    genderSelectedItem2 = position
                    bundle.putString(
                        "Cinsiyet 2",
                        listview_cinsiyet_couple2.selectedItem.toString()
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // another interface callback
                }
            }
    }


}