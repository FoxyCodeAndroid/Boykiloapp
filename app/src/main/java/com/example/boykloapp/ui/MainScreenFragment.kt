package com.example.boykloapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.foxycode.bedenolcer.R
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_single.setOnClickListener {
            val action = MainScreenFragmentDirections.mainactionsingle()
            Navigation.findNavController(it).navigate(action)
        }
        btn_couple.setOnClickListener {
            val action1 = MainScreenFragmentDirections.mainactioncouple()
            Navigation.findNavController(it).navigate(action1)
        }
        btn_about.setOnClickListener {
            val action2 = MainScreenFragmentDirections.mainactionaboutus()
            Navigation.findNavController(it).navigate(action2)
        }

    }

}