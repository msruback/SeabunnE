package com.beryl.seabunne.ui.viewHolders.salmonRun

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.R
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.ui.adapter.salmonRun.SalmonTimesAdapter

class SalmonRunListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(salmonRuns: List<SalmonRun>) {
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.times_list)

        recyclerView.adapter = SalmonTimesAdapter(itemView.context, salmonRuns)
        recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
    }
}
