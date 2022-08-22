package com.beryl.seabunne.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.beryl.seabunne.api.SplatnetDataOrchestrator
import com.beryl.seabunne.api.requests.SchedulesRequest
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.listMap
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import kotlinx.coroutines.launch

class SchedulesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = SplatnetDatabase.getDatabase(application.applicationContext)

    //Internal Live Data
    private val regular = database.timePeriodDao().selectRegular()
    private val gachi = database.timePeriodDao().selectGachi()
    private val league = database.timePeriodDao().selectLeague()

    //Exposed Live Data
    val regularSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(regular) { listMap(it, application.applicationContext) }
    val gachiSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(gachi) { listMap(it, application.applicationContext) }
    val leagueSchedule: LiveData<List<TimePeriod>> =
        Transformations.map(league) { listMap(it, application.applicationContext) }

    //Exposed Functions

    fun refresh() {
        viewModelScope.launch {
            SplatnetDataOrchestrator.requestData(SchedulesRequest(database))
        }
    }
}