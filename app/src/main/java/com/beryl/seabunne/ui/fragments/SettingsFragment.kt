package com.beryl.seabunne.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.views.FontTextView

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        val cookieButton = rootView.findViewById<FontTextView>(R.id.cookie)

        cookieButton.setOnClickListener {
            findNavController().navigate(R.id.cookieDialogFragment)
        }
        return rootView
    }
}