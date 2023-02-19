package com.example.murojatandijonapp.main.fragment.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.model.Person
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAdd2Binding

class Add2Fragment : BaseFragment(R.layout.fragment_add2) {
    private val binding by viewBinding { FragmentAdd2Binding.bind(it) }
    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = arguments?.getParcelable("person")
        Log.d("@@@", "onCreate: ${person}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        person?.let {
            binding.btnSave.click {

            }
        }
    }
}