package com.beryl.seabunne.ui.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.beryl.seabunne.api.OrchestratorResponse
import com.beryl.seabunne.api.SplatnetDataOrchestrator
import com.beryl.seabunne.api.requests.SalmonRunScheduleRequest
import com.beryl.seabunne.api.requests.SchedulesRequest
import com.beryl.seabunne.api.requests.TimelineRequest
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.listMap
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun

class SchedulesViewModel(application: Application) : SplatnetViewModel(application) {

    private val database = SplatnetDatabase.getDatabase(application.applicationContext)

    //Internal Live Data
    private val regular = database.timePeriodDao().selectRegular()
    private val gachi = database.timePeriodDao().selectGachi()
    private val league = database.timePeriodDao().selectLeague()
    private val salmonRun = database.salmonRunDao().selectAll()

    //Exposed Live Data
    val regularSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(regular) { listMap(it, application.applicationContext) }
    val gachiSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(gachi) { listMap(it, application.applicationContext) }
    val leagueSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(league) { listMap(it, application.applicationContext) }
    val salmonRunSchedule: LiveData<List<SalmonRun>> =
        Transformations.map(salmonRun) { listMap(it, application.applicationContext) }


    override suspend fun getData(): OrchestratorResponse<Int> =
        SplatnetDataOrchestrator.requestData(
            getApplication<Application>().applicationContext,
            SchedulesRequest(database),
            SalmonRunScheduleRequest(
                database,
                getApplication<Application>().applicationContext
            ),
            TimelineRequest(database, getApplication<Application>().applicationContext)
        )

}
