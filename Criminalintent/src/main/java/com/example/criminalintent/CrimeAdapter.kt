package com.example.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ItemCrimeBinding


class CrimeAdapter(private val crimes: List<Crime>, val onClick: (Crime) -> Unit) :
    ListAdapter<Crime, CrimeAdapter.CrimeViewHolder>(object: DiffUtil.ItemCallback<Crime>() {
        override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeAdapter.CrimeViewHolder {
        return CrimeViewHolder(
            ItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount(): Int {
        return crimes.size
    }

    inner class CrimeViewHolder(private val binding: ItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Crime) {
            binding.crimeNumberTextView.text = item.title.toString()
            binding.occurredDateTextView.text = item.date.toString()
            binding.crimeImageView.visibility= if (item.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }
}


