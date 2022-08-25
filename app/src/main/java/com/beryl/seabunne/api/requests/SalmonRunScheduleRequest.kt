package com.beryl.seabunne.api.requests

import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonSchedule
import retrofit2.Response
import java.util.*

class SalmonRunScheduleRequest(private val database: SplatnetDatabase) :
    SplatnetRequest<SalmonSchedule>() {
    override val name: String = "Salmon Run Schedules Endpoint"

    override suspend fun call(): Response<SalmonSchedule> =
        splatnet.getSalmonRunSchedule(cookie, uniqueId)

    override fun manageResponse(response: Response<SalmonSchedule>) {
        val salmonRunSchedule = response.body()
        salmonRunSchedule?.detailedShifts?.forEach {
            it.stow(database)
        }
        salmonRunSchedule?.times?.forEach {
            it.stow(database)
        }
    }

    override fun shouldUpdate(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis / 1000
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