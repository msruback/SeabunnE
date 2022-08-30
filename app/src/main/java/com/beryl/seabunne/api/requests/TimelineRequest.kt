package com.beryl.seabunne.api.requests

import android.content.Context
import com.beryl.seabunne.R
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.splatnet2.Timeline
import retrofit2.Response
import java.util.*

class TimelineRequest(private val database: SplatnetDatabase, private val context: Context) :
    SplatnetRequest<Timeline>() {
    override val name: String = "Timeline Endpoint"

    override suspend fun call(): Response<Timeline> = splatnet.getTimeline(cookie, uniqueId)

    override fun manageResponse(response: Response<Timeline>) {
        val timeline = response.body()

        context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        ).edit().putString("unique_id", timeline?.uniqueID).apply()

        val run = database.salmonRunDao().selectCurrent(Date().time / 1000).toSplatnet(context)
        if (run.gear == null) {
            run.gear = timeline?.currentRun?.rewardGear?.gear
            run.stow(database)
        }

    }

    override fun shouldUpdate(): Boolean {
        return database.salmonRunDao()
            .containsCurrentRunWithoutGear(Date().time / 1000) || uniqueId.isBlank()
    }
}