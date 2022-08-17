package com.beryl.seabunsource.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.beryl.seabunsource.api.SplatnetDataOrchestrator
import com.beryl.seabunsource.api.requests.SchedulesRequest
import com.beryl.seabunsource.data.database.SplatnetDatabase
import com.beryl.seabunsource.data.database.listMap
import com.beryl.seabunsource.data.splatnet2.TimePeriod
import kotlinx.coroutines.launch

class SchedulesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = SplatnetDatabase.getDatabase(application.applicationContext)

    //Internal Live Data
    private val regular = database.timePeriodDao().getRegular()
    private val gachi = database.timePeriodDao().getGachi()
    private val league = database.timePeriodDao().getLeague()

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