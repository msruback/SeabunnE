package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage

@Entity(tableName = "SalmonRunStages")
data class SalmonRunStageEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Image") val image: String
) {

    fun toSplatnet(): SalmonRunStage = SalmonRunStage(name, image)
}
