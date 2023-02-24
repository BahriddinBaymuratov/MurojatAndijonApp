package com.example.murojatandijonapp.main.fragment.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Person
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = arguments?.getParcelable("problem")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        person?.let {
            binding.apply {
                titleFullName.text = "${person!!.name} ${person!!.lastName}"
                streetName.text = person!!.street
                fullName.text = "${person!!.name} ${person!!.lastName} "
                young.text = person!!.young
                gender.text = person!!.gender
                description.text = person!!.description
                streetName.text = person!!.street
                street.text = person!!.street
                number.text = person!!.number
            }
        }
        binding.btnBack.click {
            findNavController().popBackStack()
        }
        binding.btnCall.click {
            if (!isCallPhonePermissionAvailable()) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    100
                )
            } else {
                callPhone()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone()
            }
        }
    }

    private fun callPhone() {
        val call = person!!.number
        val intent = Intent(Intent.ACTION_CALL)
        val uri = Uri.parse("tel:${call}")
        intent.data = uri
        startActivity(intent)
    }

    private fun isCallPhonePermissionAvailable() =
        ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED

}