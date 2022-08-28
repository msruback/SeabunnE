package com.beryl.seabunne.ui.viewHolders.salmonRun

import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.databinding.ItemCurrentSalmonRunBinding

class CurrentRunViewHolder(private val binding: ItemCurrentSalmonRunBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(salmonRun: SalmonRun) {
        binding.shift = salmonRun
    }
}
