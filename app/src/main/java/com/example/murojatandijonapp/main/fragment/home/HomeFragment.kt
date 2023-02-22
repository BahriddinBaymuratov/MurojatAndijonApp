package com.example.murojatandijonapp.main.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.domain.model.Person
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentHomeBinding
import com.example.murojatandijonapp.main.fragment.PersonAdapter
import com.example.murojatandijonapp.main.fragment.detail.DetailViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val personAdapter by lazy { PersonAdapter() }
    private val viewModel: HomeViewModel by viewModels()
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val problemList: MutableList<Person> = mutableListOf()

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
        initViews()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
            adapter = personAdapter
        }
        fireStore.collection("problem").get().addOnCompleteListener {
            problemList.clear()
            binding.prg.isVisible = false
            if (it.isSuccessful){
                val problem = it.result.toObjects(Person::class.java)
                problemList.addAll(problem)
            }
            personAdapter.submitList(problemList)
            personAdapter.notifyDataSetChanged()
        }
        personAdapter.onClick = {
            val bundle = bundleOf("problem" to it)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.state.collect() {
                when (it) {
                    is HomeState.Idle -> {
                        binding.prg.isVisible = true
                    }
                    is HomeState.Loading -> {
                        binding.prg.isVisible = true
                    }
                    is HomeState.Error -> {
                        binding.prg.isVisible = false
                        snackBar(it.message)
                    }
                    is HomeState.Success -> {
                        binding.prg.isVisible = false
                        personAdapter.submitList(it.list)
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