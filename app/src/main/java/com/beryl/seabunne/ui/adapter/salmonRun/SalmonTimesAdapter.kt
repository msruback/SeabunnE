package com.beryl.seabunne.ui.adapter.salmonRun

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.databinding.ItemSalmonTimeBinding
import com.beryl.seabunne.ui.viewHolders.salmonRun.SalmonTimeViewHolder

class SalmonTimesAdapter(val context: Context, private val shifts: List<SalmonRun>) :
    RecyclerView.Adapter<SalmonTimeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalmonTimeViewHolder =
        SalmonTimeViewHolder(
            ItemSalmonTimeBinding.inflate(inflater, parent, false)
        )

    override fun onBindViewHolder(holder: SalmonTimeViewHolder, position: Int) =
        holder.bind(shifts[position])

    override fun getItemCount(): Int = shifts.size
}