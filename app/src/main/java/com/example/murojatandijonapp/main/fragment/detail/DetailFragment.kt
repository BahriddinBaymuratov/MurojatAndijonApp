package com.example.murojatandijonapp.main.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentDetailBinding
import com.example.murojatandijonapp.main.fragment.PersonAdapter

//class DetailFragment : BaseFragment(R.layout.fragment_detail) {
//    private var _binding : FragmentDetailBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: DetailViewModel by viewModels()
//    private val adapter by lazy { PersonAdapter() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentDetailBinding.inflate(inflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//}