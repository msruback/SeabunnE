package com.beryl.seabunsource.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.beryl.seabunsource.data.splatnet2.entities.Stage

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