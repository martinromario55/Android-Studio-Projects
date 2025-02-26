package com.tuyiiya.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tuyiiya.flagquiz.R
import com.tuyiiya.flagquiz.database.DatabaseCopyHelper
import com.tuyiiya.flagquiz.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        createAndOpenDatabase()

        fragmentHomeBinding.buttonStart.setOnClickListener {
            val direction = FragmentHomeDirections.actionFragmentHomeToFragmentQuiz()
            this.findNavController().navigate(direction)
        }

        return fragmentHomeBinding.root
    } // End of onCreateView

    private fun createAndOpenDatabase() {
        try {
            val helper = DatabaseCopyHelper(requireActivity())
            helper.createDataBase()
            helper.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } // End of createAndOpenDatabase
}