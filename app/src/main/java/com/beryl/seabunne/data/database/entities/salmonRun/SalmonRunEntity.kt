package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.*
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon

@Entity(
    tableName = "SalmonRuns",
    indices = [Index(value = ["StageId"], unique = false)],
    foreignKeys = [
        ForeignKey(
            entity = SalmonRunStageEntity::class,
            parentColumns = ["Id"],
            childColumns = ["StageId"]
        )
    ]
)
data class SalmonRunEntity(
    @ColumnInfo(name = "StageId") val stage: Int,
    @PrimaryKey @ColumnInfo(name = "StartTime") val start: Long,
    @ColumnInfo(name = "EndTime") val end: Long
) {
    fun toSplatnet(stage: SalmonRunStage, weapons: List<SalmonRunWeapon>): SalmonRun =
        SalmonRun(stage, weapons, start, end)
}
