package com.beryl.seabunne.ui.adapter.salmonRun

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.R
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.databinding.ItemCurrentSalmonRunBinding
import com.beryl.seabunne.databinding.ItemNextSalmonRunBinding
import com.beryl.seabunne.ui.viewHolders.salmonRun.CurrentRunViewHolder
import com.beryl.seabunne.ui.viewHolders.salmonRun.NextRunViewHolder
import com.beryl.seabunne.ui.viewHolders.salmonRun.SalmonRunListViewHolder

class ShiftAdapter(val context: Context, shifts: List<SalmonRun>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val detailedShifts: List<SalmonRun>
    private val otherShifts: List<SalmonRun>

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    init {
        val detailedShifts = mutableListOf<SalmonRun>()
        val otherShifts = mutableListOf<SalmonRun>()
        shifts.forEach {
            if (it.stage != null && it.weapons != null) {
                detailedShifts.add(it)
            } else {
                otherShifts.add(it)
            }
        }

        this.detailedShifts = detailedShifts.toList()
        this.otherShifts = otherShifts.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> CurrentRunViewHolder(ItemCurrentSalmonRunBinding.inflate(inflater, parent, false))
            1 -> NextRunViewHolder(ItemNextSalmonRunBinding.inflate(inflater, parent, false))
            else -> SalmonRunListViewHolder(
                inflater.inflate(
                    R.layout.item_salmon_time_list,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as CurrentRunViewHolder).bind(detailedShifts[position])
            1 -> (holder as NextRunViewHolder).bind(detailedShifts[position])
            2 -> (holder as SalmonRunListViewHolder).bind(otherShifts)
        }
    }

    override fun getItemCount(): Int {
        return detailedShifts.size + if (otherShifts.isNotEmpty()) {
            1
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (detailedShifts.size - 1 >= position) {
            if (detailedShifts[position].gear != null) {
                0
            } else {
                1
            }
        } else {
            2
        }
    }
}