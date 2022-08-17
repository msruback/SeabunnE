package com.beryl.seabunsource.data.splatnet2

import android.os.Parcelable
import com.beryl.seabunsource.data.database.SplatnetDatabase
import com.beryl.seabunsource.data.database.entities.TimePeriodEntity
import com.beryl.seabunsource.data.database.entities.TimePeriodStages
import com.beryl.seabunsource.data.splatnet2.entities.Stage
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TimePeriod(
    @SerializedName("game_mode") val gameMode: KeyName,
    @SerializedName("rule") val rule: KeyName,
    @SerializedName("stage_a") val stageA: Stage,
    @SerializedName("stage_b") val stageB: Stage,
    @SerializedName("start_time") val start: Long,
    @SerializedName("end_time") val end: Long
) : Parcelable {

    private fun toRoom(): TimePeriodEntity {

        val cal = Calendar.getInstance()
        cal.time = Date(start * 1000)
        var id = cal.get(Calendar.HOUR_OF_DAY)
        when (gameMode.key) {
            "gachi" -> id += 100
            "league" -> id += 200
        }
        return TimePeriodEntity(id, gameMode.key, rule.key, start, end)
    }

    fun stow(database: SplatnetDatabase) {
        val toStow = toRoom()
        if (!database.timePeriodDao().contains(toStow.id)) {
            database.stageDao().insertAll(stageA, stageB)
            database.timePeriodDao().insertAll(toStow)
            database.timePeriodStagesDao()
                .insertAll(
                    TimePeriodStages(toStow.id, stageA.id),
                    TimePeriodStages(toStow.id, stageB.id)
                )
        }
    }
}