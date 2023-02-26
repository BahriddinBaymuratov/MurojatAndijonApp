package com.example.murojatandijonapp.main.fragment.addresume

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Person
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAdd2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class Add2Fragment : BaseFragment(R.layout.fragment_add2) {
    private var _binding: FragmentAdd2Binding? = null
    private val binding get() = _binding!!
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private lateinit var photoUrl: Uri
    private var person: Person? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = arguments?.getParcelable("person")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAdd2Binding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.btnPhoto.click {
            launcher.launch("image/*")
            binding.btnPhotoVsb.isVisible = false
            binding.imageViewVsb.isVisible = true
        }
        person?.let {
            binding.btnSave.click {
                val problem = binding.problem.text.toString().trim()

                if (problem.isNotBlank() && ::photoUrl.isInitialized) {
                    saveToFireBase(
                        person!!.name,
                        person!!.lastName,
                        person!!.young,
                        person!!.street,
                        person!!.address,
                        person!!.number,
                        person!!.gender,
                        problem,
                        person!!.image
                    )
                    Toast.makeText(requireContext(), "Sizning ma'lumotlaringiz saqlandi!!", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("problem" to person!!)
                    findNavController().navigate(R.id.action_add2Fragment_to_homeFragment, bundle)
                } else {
                    Toast.makeText(requireContext(), "Enter problem!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveToFireBase(
        name: String,
        lastName: String,
        young: String,
        street: String,
        address: String,
        number: String,
        gender: String,
        description: String,
        image: String
    ) {
        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/$fileName")
        ref.putFile(photoUrl)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    fireStore.collection("problem").document().set(
                        Person(
                            name,
                            lastName,
                            young,
                            street,
                            address,
                            number,
                            gender,
                            description,
                            image
                        )
                    )
                }
            }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let { uri ->
            photoUrl = uri
            binding.imageView.setImageURI(uri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}