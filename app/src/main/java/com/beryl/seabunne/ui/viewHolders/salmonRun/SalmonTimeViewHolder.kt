package com.beryl.seabunne.ui.viewHolders.salmonRun

import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.databinding.ItemSalmonTimeBinding

class SalmonTimeViewHolder(private val binding: ItemSalmonTimeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(salmonRun: SalmonRun) {
        binding.shift = salmonRun
    }
}
