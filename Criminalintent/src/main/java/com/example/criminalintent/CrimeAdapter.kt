package com.example.criminalintent

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ItemCrimeBinding
import com.example.criminalintent.databinding.ItemCrimePoliceBinding
import kotlinx.coroutines.NonDisposableHandle.parent

private const val POLICE = 100
private const val NO_POLICE = 200

class CrimeAdapter(private val crimes: List<Crime>, val onClick: (Crime) -> Unit) :
    RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeViewHolder {
        return when (viewType) {
            POLICE -> PoliceViewHolder(
                ItemCrimePoliceBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> NoPoliceViewHolder(
                ItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount(): Int {
        return crimes.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) POLICE else NO_POLICE
    }

    open inner class CrimeViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        open fun bind(item: Crime) {}
    }

    inner class PoliceViewHolder(private val binding: ItemCrimePoliceBinding) :
        CrimeViewHolder(binding.root) {
        override fun bind(item: Crime) {
            binding.crimeNumberTextView.text = item.title.toString()
            binding.occurredDateTextView.text = item.date.toString()
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    inner class NoPoliceViewHolder(private val binding: ItemCrimeBinding) :
        CrimeViewHolder(binding.root) {
        override fun bind(item: Crime) {
            binding.crimeNumberTextView.text = item.title.toString()
            binding.occurredDateTextView.text = item.date.toString()
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

}





