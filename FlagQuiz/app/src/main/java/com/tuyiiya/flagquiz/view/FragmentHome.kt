package com.tuyiiya.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuyiiya.flagquiz.R
import com.tuyiiya.flagquiz.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        fragmentHomeBinding.buttonStart.setOnClickListener {

        }

        return fragmentHomeBinding.root
    } // End of onCreateView
}