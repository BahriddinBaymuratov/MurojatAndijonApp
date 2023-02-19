package com.example.murojatandijonapp.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentHomeBinding
import com.example.murojatandijonapp.main.fragment.PersonAdapter
import com.example.murojatandijonapp.main.fragment.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { PersonAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.search.click {
//            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
//        }
        initViews()

    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapter
        }
        adapter.onClick = {

        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collect() {
                when (it) {
                    is HomeState.Idle -> Unit
                    is HomeState.Loading -> Unit
                    is HomeState.Error -> {
                        snackBar(it.message)
                    }
                    is HomeState.Success -> {
                        adapter.submitList(it.list)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}