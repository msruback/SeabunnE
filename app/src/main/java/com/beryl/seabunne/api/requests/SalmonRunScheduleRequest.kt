package com.beryl.seabunne.api.requests

import android.content.Context
import com.beryl.seabunne.api.SplatnetDataOrchestrator
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonSchedule
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class SalmonRunScheduleRequest(
    private val database: SplatnetDatabase,
    private val context: Context
) :
    SplatnetRequest<SalmonSchedule>() {
    override val name: String = "Salmon Run Schedules Endpoint"

    override suspend fun call(): Response<SalmonSchedule> =
        splatnet.getSalmonRunSchedule(cookie, uniqueId)

    override fun manageResponse(response: Response<SalmonSchedule>) {
        val salmonRunSchedule = response.body()

        val currentTime = Date().time / 1000
        var getGear = false
        salmonRunSchedule?.detailedShifts?.forEach {
            it.stow(database)
            if (it.start < currentTime) {
                getGear = true
            }
        }
        salmonRunSchedule?.times?.forEach {
            it.stow(database)
        }

        if (getGear) {
            MainScope().launch {
                SplatnetDataOrchestrator.requestData(TimelineRequest(database, context))
            }
        }
    }

    override fun shouldUpdate(): Boolean {
        val currentTime = Date().time / 1000
        return if (database.salmonRunDao().count() < 5 || database.salmonRunDao()
                .containsExpiredRuns(currentTime)
        ) {
            database.salmonRunDao().deleteExpiredRuns(currentTime)
            true
        } else {
            false
        }
    }
}
