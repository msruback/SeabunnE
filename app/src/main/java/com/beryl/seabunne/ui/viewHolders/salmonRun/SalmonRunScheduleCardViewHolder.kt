package com.beryl.seabunne.ui.viewHolders.salmonRun

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.seabun.Mode
import com.beryl.seabunne.data.seabun.Modes
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.databinding.CardRegularBinding
import com.beryl.seabunne.ui.adapter.salmonRun.ShiftAdapter
import kotlin.random.Random

class SalmonRunScheduleCardViewHolder(
    val context: Context,
    private val binding: CardRegularBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val mode: Mode = Modes.salmonRun

    init {
        PagerSnapHelper().attachToRecyclerView(binding.pager)
    }

    fun bind(salmonRunList: List<SalmonRun>) {
        binding.primaryColor = mode.getPrimaryColor(context)
        binding.secondaryColor = mode.getSecondaryColor(context)
        binding.tertiaryColor = mode.getTertiaryColor(context)

        binding.root.contentDescription = "Schedule for ${mode.getName(context)}"
        binding.card.clipToOutline = true

        val isNegative = Random.nextBoolean()
        if (isNegative) {
            binding.outlineAccent.rotation = Random.nextFloat() * -1
            binding.outlinePrimary.rotation = Random.nextFloat()
        } else {
            binding.outlineAccent.rotation = Random.nextFloat()
            binding.outlinePrimary.rotation = Random.nextFloat() * -1
        }

        binding.title.text = mode.getName(context)
        binding.pager.adapter = ShiftAdapter(context, salmonRunList)
        binding.pager.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }
}