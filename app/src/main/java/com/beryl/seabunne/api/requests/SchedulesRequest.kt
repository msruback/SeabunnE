package com.beryl.seabunne.api.requests

import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.splatnet2.battles.Schedules
import retrofit2.Response
import java.util.*

class SchedulesRequest(private val database: SplatnetDatabase) : SplatnetRequest<Schedules>() {

    override val name: String = "Schedules Endpoint"

    override suspend fun call(): Response<Schedules> = splatnet.getSchedules(cookie, uniqueId)

    override fun manageResponse(response: Response<Schedules>) {
        val schedules = response.body()
        schedules?.regular?.forEach {
            it.stow(database)
        }
        schedules?.ranked?.forEach {
            it.stow(database)
        }
        schedules?.league?.forEach {
            it.stow(database)
        }
    }

    //If there are less than 12 regular time periods that are current, update
    override fun shouldUpdate(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis / 1000
        return if (database.timePeriodDao().countRegular() < 12 || database.timePeriodDao()
                .containsExpiredTimePeriods(currentTime)
        ) {
            database.timePeriodDao().deleteExpiredTimePeriods(currentTime)
            true
        } else {
            false
        }
    }
}