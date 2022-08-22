package com.beryl.seabunne.ui.adapter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import com.beryl.seabunne.databinding.ItemCompetitiveBinding
import com.beryl.seabunne.databinding.ItemRegularBinding

class TimePeriodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var regularBinding: ItemRegularBinding
    private lateinit var competitiveBinding: ItemCompetitiveBinding
    private var isCompetitive = false

    constructor(binding: ItemRegularBinding) :
            this(binding.root) {
        regularBinding = binding
    }

    constructor(binding: ItemCompetitiveBinding) :
            this(binding.root) {
        competitiveBinding = binding
        isCompetitive = true
    }

    fun bind(timePeriod: TimePeriod) {
        if (isCompetitive) {
            competitiveBinding.timePeriod = timePeriod
        } else {
            regularBinding.timePeriod = timePeriod
        }
    }
}
