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
import com.example.boykloapp.repository.IndexCalculationRepository
import com.example.boykloapp.utils.MaleUtil
import com.example.boykloapp.utils.MyUtils
import com.example.boykloapp.utils.SettingsUtils
import com.foxycode.bedenolcer.R
import com.foxycode.bedenolcer.databinding.FragmentCoupleBinding
import com.google.android.play.core.review.ReviewManager
import kotlinx.android.synthetic.main.fragment_couple.*
import leakcanary.AppWatcher

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

        if (SettingsUtils.chosenSettings == false){
          binding.coupleMassWeightText1.text = "lb"
          binding.coupleMassWeightText2.text = "lb"
          binding.coupleMassHeightText1.text = "in"
          binding.coupleMassHeightText2.text = "in"
        }

        binding.btnCoupleResult.setOnClickListener {
            val cm = binding.coupleEditHeight.text.toString()
            val kg = binding.coupleEditWeight.text.toString()
            val cm2 = binding.coupleEditHeight2.text.toString()
            val kg2 = binding.coupleEditWeight2.text.toString()

            if (SettingsUtils.chosenSettings==true){
                if (checkEmpty(binding.coupleEditHeight.text.toString(), binding.coupleEditWeight.text.toString())) {
                    result1 = IndexCalculationRepository().calculationBodyCmKg(cm.toFloat(),kg.toFloat())
                    binding.coupleResultText.text = IndexCalculationRepository().resCal(result1,requireContext())
                    binding.pbCouple.currentProgress = result1.toInt()/2
                    binding.pbCouple.visibility = View.VISIBLE
                } else
                    IndexCalculationRepository().showErrorToast(requireContext())

                if (checkEmpty(binding.coupleEditHeight2.text.toString(), binding.coupleEditWeight2.text.toString(),)) {
                    result2 = IndexCalculationRepository().calculationBodyCmKg(cm2.toFloat(),kg2.toFloat())
                    binding.coupleResultText2.text = IndexCalculationRepository().resCal(result2,requireContext())
                    binding.pbCouple2.currentProgress = result2.toInt()/2
                    binding.pbCouple2.visibility = View.VISIBLE
                } else
                    IndexCalculationRepository().showErrorToast(requireContext())
            }else if(SettingsUtils.chosenSettings == false){
                if (checkEmpty(binding.coupleEditHeight.text.toString(), binding.coupleEditWeight.text.toString())) {
                    result1 = IndexCalculationRepository().calculationBodyInchPound(cm.toFloat(),kg.toFloat())
                    binding.coupleResultText.text = IndexCalculationRepository().resCal(result1,requireContext())
                    binding.pbCouple.currentProgress = result1.toInt()/2
                    binding.pbCouple.visibility = View.VISIBLE
                } else
                    IndexCalculationRepository().showErrorToast(requireContext())

                if (checkEmpty(binding.coupleEditHeight2.text.toString(), binding.coupleEditWeight2.text.toString(),)) {
                    result2 = IndexCalculationRepository().calculationBodyInchPound(cm2.toFloat(),kg2.toFloat())
                    binding.coupleResultText2.text = IndexCalculationRepository().resCal(result2,requireContext())
                    binding.pbCouple2.currentProgress = result2.toInt()/2
                    binding.pbCouple2.visibility = View.VISIBLE
                } else
                    IndexCalculationRepository().showErrorToast(requireContext())
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
    private fun setView() {
        binding.coupleResultText.movementMethod= ScrollingMovementMethod()
        binding.coupleResultText2.movementMethod= ScrollingMovementMethod()
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