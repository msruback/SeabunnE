package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity

@Entity(
    tableName = "SalmonRunWeapons",
    primaryKeys = ["SalmonRunId", "Position"],
    indices = [Index(value = ["WeaponId"], unique = false)],
    foreignKeys = [
        ForeignKey(
            entity = SalmonRunEntity::class,
            parentColumns = ["StartTime"],
            childColumns = ["SalmonRunId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WeaponEntity::class,
            parentColumns = ["Id"],
            childColumns = ["WeaponId"]
        )
    ]
)
data class SalmonRunWeaponEntity(
    @ColumnInfo(name = "SalmonRunId") val salmonRunId: Long,
    @ColumnInfo(name = "Position") val position: Int,
    @ColumnInfo(name = "WeaponType") val weaponType: String,
    @ColumnInfo(name = "WeaponId") val weaponId: Int? = null
)
