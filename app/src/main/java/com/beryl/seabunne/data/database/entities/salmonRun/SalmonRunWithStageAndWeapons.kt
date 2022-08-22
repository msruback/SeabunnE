package com.beryl.seabunne.data.database.entities.salmonRun

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.database.entities.userInfo.WeaponWithSpecialAndSub

data class SalmonRunWithStageAndWeapons(
    @Embedded val salmonRun: SalmonRunEntity,
    @Relation(
        entity = SalmonRunStageEntity::class,
        parentColumn = "Id",
        entityColumn = "StageId"
    ) val salmonRunStage: SalmonRunStageEntity,
    @Relation(
        entity = WeaponEntity::class,
        parentColumn = "StartTime",
        entityColumn = "Id",
        associateBy = Junction(
            value = SalmonRunWeapons::class,
            parentColumn = "SalmonRunId",
            entityColumn = "WeaponId"
        )
    )
    val weapons: List<WeaponWithSpecialAndSub>
)
