package com.beryl.seabunne.data.database.entities.battles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage

@Entity(
    primaryKeys = ["TimePeriodId", "StageId"],
    indices = [Index(value = ["StageId"], unique = false)],
    foreignKeys = [
        ForeignKey(
            entity = TimePeriodEntity::class,
            parentColumns = ["Id"],
            childColumns = ["TimePeriodId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Stage::class,
            parentColumns = ["Id"],
            childColumns = ["StageId"]
        )
    ]
)
data class TimePeriodStages(
    @ColumnInfo(name = "TimePeriodId") val timePeriodId: Int,
    @ColumnInfo(name = "StageId") val stageId: Int
)
