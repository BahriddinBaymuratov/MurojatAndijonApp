package com.example.murojatandijonapp.main.fragment

import android.os.Bundle
import android.view.View
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAddBinding


class AddFragment : BaseFragment(R.layout.fragment_add) {
    private val binding by viewBinding { FragmentAddBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}