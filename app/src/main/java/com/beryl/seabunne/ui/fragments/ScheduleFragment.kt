package com.beryl.seabunne.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.adapter.SchedulerAdapter
import com.beryl.seabunne.ui.viewModels.SchedulesViewModel


class ScheduleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_schedule, container, false)

        val schedulesViewModel: SchedulesViewModel by viewModels()
        val schedules = rootView.findViewById<RecyclerView>(R.id.scheduleRecyclerView)
        schedules.adapter = SchedulerAdapter(requireContext())
        schedules.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        schedulesViewModel.refresh()

        schedulesViewModel.regularSchedule.observe(
            viewLifecycleOwner
        ) { (schedules.adapter as SchedulerAdapter).updateRegularList(it) }

        schedulesViewModel.gachiSchedule.observe(
            viewLifecycleOwner
        ) { (schedules.adapter as SchedulerAdapter).updateGachiList(it) }

        schedulesViewModel.leagueSchedule.observe(
            viewLifecycleOwner
        ) { (schedules.adapter as SchedulerAdapter).updateLeagueList(it) }

        schedulesViewModel.salmonRunSchedule.observe(
            viewLifecycleOwner
        ) { (schedules.adapter as SchedulerAdapter).updateSalmonRunList(it) }

        return rootView
    }
}