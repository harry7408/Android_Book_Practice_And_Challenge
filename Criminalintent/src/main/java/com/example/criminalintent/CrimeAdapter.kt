package com.example.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
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

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) POLICE else NO_POLICE
    }

    inner class CrimeViewHolder(private val binding: ItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Crime) {
            binding.crimeNumberTextView.text = item.title.toString()
            binding.occurredDateTextView.text = item.date.toString()
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }
}


