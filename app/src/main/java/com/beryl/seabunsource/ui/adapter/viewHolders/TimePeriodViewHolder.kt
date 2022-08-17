package com.beryl.seabunsource.ui.adapter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunsource.data.splatnet2.TimePeriod
import com.beryl.seabunsource.databinding.ItemCompetitiveBinding
import com.beryl.seabunsource.databinding.ItemRegularBinding

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
