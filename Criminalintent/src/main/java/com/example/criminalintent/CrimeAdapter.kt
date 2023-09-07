package com.example.criminalintent

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ItemCrimeBinding
import java.text.SimpleDateFormat
import java.util.*

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

    inner class CrimeViewHolder(private val binding: ItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Crime) {
            binding.crimeNumberTextView.text = item.title.toString()
            binding.occurredDateTextView.text = SimpleDateFormat(
                "EEEE, MMM dd, yyyy",Locale.KOREA).format(item.date)
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


