package com.example.murojatandijonapp.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.databinding.FragmentAdd2Binding

class Add2Fragment : Fragment(R.layout.fragment_add2) {
    private val binding by viewBinding { FragmentAdd2Binding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}