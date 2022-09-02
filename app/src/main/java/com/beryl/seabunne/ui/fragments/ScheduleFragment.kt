package com.beryl.seabunne.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.adapter.SchedulerAdapter
import com.beryl.seabunne.ui.viewModels.SchedulesViewModel


class ScheduleFragment : SplatnetFragment<SchedulesViewModel>() {

    override val viewModel: SchedulesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_schedule, container, false)
        val schedules = rootView.findViewById<RecyclerView>(R.id.scheduleRecyclerView)
        schedules.adapter = SchedulerAdapter(requireContext())
        schedules.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModel.refresh()

        viewModel.regularSchedule.observe(viewLifecycleOwner) {
            (schedules.adapter as SchedulerAdapter).updateRegularList(it)
        }

        viewModel.gachiSchedule.observe(viewLifecycleOwner) {
            (schedules.adapter as SchedulerAdapter).updateGachiList(it)
        }

        viewModel.leagueSchedule.observe(viewLifecycleOwner) {
            (schedules.adapter as SchedulerAdapter).updateLeagueList(it)
        }

        viewModel.salmonRunSchedule.observe(viewLifecycleOwner) {
            (schedules.adapter as SchedulerAdapter).updateSalmonRunList(it)
        }

        return rootView
    }
}