package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity

@Entity(
    primaryKeys = ["SalmonRunId", "WeaponId"],
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
data class SalmonRunWeapons(
    @ColumnInfo(name = "SalmonRunId") val salmonRunId: Long,
    @ColumnInfo(name = "WeaponId") val weaponId: Int
)
