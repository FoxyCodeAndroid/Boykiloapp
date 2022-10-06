package com.example.boykloapp.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.foxycode.bedenolcer.databinding.FragmentAboutUsBinding
import kotlinx.android.synthetic.main.fragment_splash.*


class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!
    private val LINK = "https://github.com/FoxyCodeAndroid/Boykiloapp"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtViewLink.isClickable = true
        binding.txtViewLink.movementMethod = LinkMovementMethod.getInstance()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtViewLink.text = Html.fromHtml(LINK, Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.txtViewLink.text = Html.fromHtml(LINK)
        }
    }
}