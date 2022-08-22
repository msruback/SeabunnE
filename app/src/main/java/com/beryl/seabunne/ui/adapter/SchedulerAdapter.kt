package com.beryl.seabunne.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.data.seabun.Modes
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import com.beryl.seabunne.databinding.CardRegularBinding
import com.beryl.seabunne.ui.adapter.viewHolders.BattleScheduleCardViewHolder

class SchedulerAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rotation: MutableList<String> = mutableListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var regularList: List<TimePeriod> = listOf()
    private var gachiList: List<TimePeriod> = listOf()
    private var leagueList: List<TimePeriod> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> BattleScheduleCardViewHolder(
                context,
                Modes.regular,
                CardRegularBinding.inflate(inflater, parent, false)
            )
            1 -> BattleScheduleCardViewHolder(
                context,
                Modes.gachi,
                CardRegularBinding.inflate(inflater, parent, false)
            )
            2 -> BattleScheduleCardViewHolder(
                context,
                Modes.league,
                CardRegularBinding.inflate(inflater, parent, false)
            )
            else -> BattleScheduleCardViewHolder(
                context,
                Modes.regular,
                CardRegularBinding.inflate(inflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as BattleScheduleCardViewHolder).bind(regularList)
            1 -> (holder as BattleScheduleCardViewHolder).bind(gachiList)
            2 -> (holder as BattleScheduleCardViewHolder).bind(leagueList)
        }
    }

    override fun getItemCount(): Int = rotation.size

    override fun getItemViewType(position: Int): Int {
        return when (rotation[position]) {
            "regular" -> 0
            "ranked" -> 1
            "league" -> 2
            "fes" -> 3
            "salmon" -> 4
            else -> -1
        }
    }

    fun updateRegularList(regularList: List<TimePeriod>) {
        this.regularList = regularList
        if (rotation.contains("regular")) {
            notifyItemChanged(rotation.indexOf("regular"))
        } else {
            rotation.add(0, "regular")
            notifyItemInserted(0)
        }
    }

    fun updateGachiList(gachiList: List<TimePeriod>) {
        this.gachiList = gachiList
        if (rotation.contains("ranked")) {
            notifyItemChanged(rotation.indexOf("ranked"))
        } else {
            var index = 0
            if (rotation.contains("regular")) {
                index = rotation.indexOf("regular") + 1
            }
            rotation.add(index, "ranked")
            notifyItemInserted(index)
        }
    }

    fun updateLeagueList(leagueList: List<TimePeriod>) {
        this.leagueList = leagueList
        if (rotation.contains("league")) {
            notifyItemChanged(rotation.indexOf("league"))
        } else {
            var index = 0
            if (rotation.contains("ranked")) {
                index = rotation.indexOf("ranked") + 1
            } else if (rotation.contains("regular")) {
                index = rotation.indexOf("regular") + 1
            }
            rotation.add(index, "league")
            notifyItemInserted(index)
        }
    }
}