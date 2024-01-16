package com.choi.beatbox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choi.beatbox.SoundViewModel
import com.choi.beatbox.databinding.ListItemSoundBinding
import com.choi.beatbox.sounds.Sound

class SoundAdapter(private val sounds: List<Sound>) :
    RecyclerView.Adapter<SoundAdapter.SoundHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
        return SoundHolder(
             ListItemSoundBinding.inflate(
                 LayoutInflater.from(parent.context),
                 parent,
                 false
             )
         )
    }

    override fun getItemCount() = sounds.size

    override fun onBindViewHolder(holder: SoundHolder, position: Int) {
        val sound=sounds[position]
        holder.bind(sound)
    }

    // ViewHolder
    inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.viewModel = SoundViewModel()
            }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound=sound
                // layout 을 즉각 변경 하도록
                executePendingBindings()
            }
        }
    }

}