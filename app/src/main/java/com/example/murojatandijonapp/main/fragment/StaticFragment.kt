package com.example.murojatandijonapp.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAddBinding
import com.example.murojatandijonapp.databinding.FragmentStaticBinding


class StaticFragment : BaseFragment(R.layout.fragment_static) {
    private val binding by viewBinding { FragmentStaticBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}