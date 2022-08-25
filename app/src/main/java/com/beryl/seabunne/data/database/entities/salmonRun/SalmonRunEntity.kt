package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.*
import com.beryl.seabunne.data.database.entities.userInfo.Clothes
import com.beryl.seabunne.data.database.entities.userInfo.Headgear
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon
import com.beryl.seabunne.data.splatnet2.userInfo.Gear

@Entity(
    tableName = "SalmonRuns",
    indices = [
        Index(value = ["StageId"], unique = false),
        Index(value = ["HeadgearId"], unique = false),
        Index(value = ["ClothesId"], unique = false),
        Index(value = ["ShoesId"], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = SalmonRunStageEntity::class,
            parentColumns = ["Id"],
            childColumns = ["StageId"]
        ),
        ForeignKey(
            entity = Headgear::class,
            parentColumns = ["Id"],
            childColumns = ["HeadgearId"]
        ),
        ForeignKey(
            entity = Clothes::class,
            parentColumns = ["Id"],
            childColumns = ["ClothesId"]
        ),
        ForeignKey(
            entity = Shoes::class,
            parentColumns = ["Id"],
            childColumns = ["ShoesId"]
        )
    ]
)
data class SalmonRunEntity(
    @PrimaryKey @ColumnInfo(name = "StartTime") val start: Long,
    @ColumnInfo(name = "EndTime") val end: Long,
    @ColumnInfo(name = "StageId") val stage: Int? = null,
    @ColumnInfo(name = "HeadgearId") val headgear: Int? = null,
    @ColumnInfo(name = "ClothesId") val clothes: Int? = null,
    @ColumnInfo(name = "ShoesId") val shoes: Int? = null
) {
    fun toSplatnet(
        stage: SalmonRunStage?,
        weapons: List<SalmonRunWeapon>?,
        gear: Gear?
    ): SalmonRun =
        SalmonRun(start, end, stage, weapons, gear)
}
