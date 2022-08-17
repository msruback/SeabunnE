package com.beryl.seabunsource.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunsource.data.seabun.Mode
import com.beryl.seabunsource.data.seabun.Modes
import com.beryl.seabunsource.data.splatnet2.TimePeriod
import com.beryl.seabunsource.databinding.ItemCompetitiveBinding
import com.beryl.seabunsource.databinding.ItemRegularBinding
import com.beryl.seabunsource.ui.adapter.viewHolders.TimePeriodViewHolder

class TimePeriodAdapter(
    val context: Context, private val schedule: List<TimePeriod>,
    private val mode: Mode
) : RecyclerView.Adapter<TimePeriodViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimePeriodViewHolder {
        return when (mode.nameResource) {
            Modes.regular.nameResource -> TimePeriodViewHolder(
                ItemRegularBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> TimePeriodViewHolder(ItemCompetitiveBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: TimePeriodViewHolder, position: Int) {
        when (mode.nameResource) {
            Modes.regular.nameResource -> holder.bind(schedule[position])
            else -> holder.bind(schedule[position])
        }
    }

    override fun getItemCount(): Int = schedule.size
}