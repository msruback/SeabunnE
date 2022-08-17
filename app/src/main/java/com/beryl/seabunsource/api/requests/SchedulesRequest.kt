package com.beryl.seabunsource.api.requests

import android.util.Log
import com.beryl.seabunsource.data.database.SplatnetDatabase
import com.beryl.seabunsource.data.splatnet2.Schedules
import retrofit2.Response
import java.util.*

class SchedulesRequest(private val database: SplatnetDatabase) : SplatnetRequest<Schedules>() {

    override val name: String = "Schedules Endpoint"

    override suspend fun call(): Response<Schedules> = splatnet.getSchedules(cookie, uniqueId)

    override fun manageResponse(response: Response<Schedules>) {
        val schedules = response.body()!!
        schedules.regular.forEach {
            it.stow(database)
        }
        schedules.ranked.forEach {
            it.stow(database)
        }
        schedules.league.forEach {
            it.stow(database)
        }
    }

    //If there are less than 12 regular time periods that are current, update
    override fun shouldUpdate(): Boolean {
        Log.d("ScheduleRequest", database.timePeriodDao().count().toString())
        return if (database.timePeriodDao().count() == 0 || database.timePeriodDao()
                .containsExpiredTimePeriods(Calendar.getInstance().timeInMillis / 1000)
        ) {
            database.timePeriodDao()
                .deleteExpiredTimePeriods(Calendar.getInstance().timeInMillis / 1000)
            true
        } else {
            false
        }
    }
}