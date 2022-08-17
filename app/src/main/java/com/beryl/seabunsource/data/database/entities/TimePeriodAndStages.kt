package com.beryl.seabunsource.data.database.entities

import android.content.Context
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.Relation
import com.beryl.seabunsource.data.database.SplatnetTransformer
import com.beryl.seabunsource.data.splatnet2.TimePeriod
import com.beryl.seabunsource.data.splatnet2.entities.Stage

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