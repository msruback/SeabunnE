package com.beryl.seabunne.data.database.entities.battles

import android.content.Context
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage

data class TimePeriodAndStages(
    @Embedded val timePeriodData: TimePeriodEntity,
    @Relation(
        entity = Stage::class,
        parentColumn = "Id",
        entityColumn = "Id",
        associateBy = Junction(
            value = TimePeriodStages::class,
            parentColumn = "TimePeriodId",
            entityColumn = "StageId"
        )
    )
    val stages: List<Stage>
) : SplatnetTransformer<TimePeriod> {

    @Ignore
    private val emptyStageList = listOf(Stage(-1, "", ""), Stage(-1, "", ""))

    override fun toSplatnet(context: Context): TimePeriod = if (stages.isEmpty()) {
        timePeriodData.toSplatnet(emptyStageList, context)
    } else {
        timePeriodData.toSplatnet(stages, context)
    }
}
