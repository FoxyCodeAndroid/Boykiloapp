package com.example.boykloapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.foxycode.bedenolcer.R
import com.foxycode.bedenolcer.databinding.FragmentMainScreenBinding
import com.foxycode.bedenolcer.databinding.FragmentSingleBinding
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.btnSingle.setOnClickListener {
            val action = MainScreenFragmentDirections.mainactionsingle()
            Navigation.findNavController(it).navigate(action)
        }
        binding.btnCouple.setOnClickListener {
            val action1 = MainScreenFragmentDirections.mainactioncouple()
            Navigation.findNavController(it).navigate(action1)
        }
        binding.btnAbout.setOnClickListener {
            val action2 = MainScreenFragmentDirections.mainactionaboutus()
            Navigation.findNavController(it).navigate(action2)
        }

    }

}