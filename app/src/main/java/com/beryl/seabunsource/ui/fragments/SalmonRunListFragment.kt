package com.beryl.seabunsource.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beryl.seabunsource.R


class SalmonRunListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salmon_run_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SalmonRunListFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}