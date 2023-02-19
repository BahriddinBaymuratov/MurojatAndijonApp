package com.example.murojatandijonapp.main.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Person
import com.example.murojatandijonapp.databinding.ItemLayoutBinding

class PersonAdapter : ListAdapter<Person, PersonAdapter.PersonVH>(DiffCallBack()) {

    lateinit var onClick: (Person) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
        return PersonVH(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PersonVH(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            with(binding) {
                itemName.text = "${person.name + person.lastName}"
                desc.text = person.description
                streetName.text = person.street
                address.text = person.address

            }
            itemView.setOnClickListener {
                onClick(person)
            }
        }
    }
}