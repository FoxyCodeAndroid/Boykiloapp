package com.example.boykloapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.boykloapp.View.CalculationResultView
import com.example.boykloapp.presenter.CalculationPresenter
import com.example.boykloapp.presenter.Presenter
import com.example.boykloapp.utils.SettingsUtils
import com.foxycode.bedenolcer.R
import com.foxycode.bedenolcer.databinding.FragmentSingleBinding
import es.dmoral.toasty.Toasty

class SingleFragment : Fragment(),CalculationResultView {
    private var bundle = Bundle()
    private var genderList = listOf<String>()
    private var result1: Float = 0.0f
    private var genderSelectedItem1: Int? = null

    private lateinit var presenter: Presenter
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

        presenter = CalculationPresenter(this)

        if (SettingsUtils.chosenSettings == false){
            binding.singleMassText1.text = "lb"
            binding.singleMassText2.text = "in"
        }
       binding.btnSingleResult.setOnClickListener {
           val cm = binding.singleEditHeight.text.toString()
           val kg = binding.singleEditWeight.text.toString()

           if (SettingsUtils.chosenSettings==true){
               if (checkEmpty(binding.singleEditHeight.text.toString(), binding.singleEditWeight.text.toString())) {
                  result1 =  presenter.calculationBodyCmKg(cm.toFloat(),kg.toFloat())
                   binding.pb.currentProgress = result1.toInt()
                   binding.singleResultText.text= presenter.resCal(result1.toInt(),requireContext())
                   binding.pb.visibility = View.VISIBLE
               }
           }else if (SettingsUtils.chosenSettings==false){
               if (checkEmpty(binding.singleEditHeight.text.toString(), binding.singleEditWeight.text.toString())) {
                   result1 = presenter.calculationBodyInchPound(cm.toFloat(),kg.toFloat())
                   binding.pb.currentProgress = result1.toInt()
                   binding.singleResultText.text= presenter.resCal(result1.toInt(),requireContext())
                   binding.pb.visibility = View.VISIBLE
               }
           }
        }
    }
    private fun setView() {
        binding.singleResultText.movementMethod= ScrollingMovementMethod()

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

    override fun onCalculationSuccess(message: String) {
        Toasty.success(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCalculationError(message: String) {
        Toasty.error(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}