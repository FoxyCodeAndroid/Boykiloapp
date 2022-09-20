package com.example.boykloapp.ui

import android.os.Bundle
import android.text.TextUtils
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
import com.foxycode.bedenolcer.databinding.FragmentSingleBinding
import kotlinx.android.synthetic.main.fragment_single.*

class SingleFragment : Fragment() {
    private var bundle = Bundle()
    private var genderList = listOf<String>()
    private var result1: Float = 0.0f
    private var genderSelectedItem1: Int? = null

    private var _binding: FragmentSingleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
        super.onViewCreated(view, savedInstanceState)

       binding.btnSingleResult.setOnClickListener {
           binding.pb.currentProgress = 130
            if (checkEmpty(
                   binding.singleEditHeight.text.toString(),
                   binding.singleEditWeight.text.toString()
                )) {
                result1 = calBodyMass(
                   binding.singleEditHeight.text.toString().toFloat(),
                    binding.singleEditWeight.text.toString().toFloat()
                )
                binding.singleResultText.text=MyUtils.resCal(result1, requireContext())
                genderSelectedItem1?.let { MaleUtil.setImg(it) }
                binding.singleWeightText.text= "Weight :  ${binding.singleEditWeight.text}"
                binding.singleHeightText.text = "Height :  ${binding.singleEditHeight.text}"
                binding.singleAgeText.text = "Height :  ${binding.singleEditAge.text}"
                binding.pb.visibility = View.VISIBLE
                binding.singleAgeText.visibility = View.VISIBLE
                binding.singleWeightText.visibility = View.VISIBLE
                binding.singleHeightText.visibility = View.VISIBLE
                binding.singleGenderText.visibility = View.VISIBLE
            } else
                MyUtils.showErrorToast(requireContext())
            clear()
        }
    }

    private fun setView() {
        //single_result_text.movementMethod = ScrollingMovementMethod()

        genderList = resources.getStringArray(R.array.cinsiyet).toList()

        val genderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderList)

        binding.listviewCinsiyetSingle.prompt = "Cinsiyet"
        binding.listviewCinsiyetSingle.adapter = genderAdapter

        binding.listviewCinsiyetSingle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                genderSelectedItem1 = position
                bundle.putString("Cinsiyet 1", binding.listviewCinsiyetSingle.selectedItem.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Select Gender", Toast.LENGTH_SHORT).show()
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
    private fun clear(){
        binding.singleEditWeight.text.clear()
        binding.singleEditAge.text.clear()
        binding.singleEditHeight.text.clear()

    }
}