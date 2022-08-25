package com.beryl.seabunne.data.database.entities.salmonRun

import android.content.Context
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.database.entities.userInfo.*
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon
import com.beryl.seabunne.data.splatnet2.userInfo.Gear

data class SalmonRunWithStageAndWeaponsAndGear(
    @Embedded val salmonRun: SalmonRunEntity,
    @Relation(
        entity = SalmonRunStageEntity::class,
        parentColumn = "StageId",
        entityColumn = "Id"
    ) val salmonRunStage: SalmonRunStageEntity?,
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
    val weapons: List<WeaponEntity>?,
    @Relation(
        entity = Headgear::class,
        parentColumn = "HeadgearId",
        entityColumn = "Id"
    )
    val headgear: HeadgearWithBrand?,
    @Relation(
        entity = Clothes::class,
        parentColumn = "ClothesId",
        entityColumn = "Id"
    )
    val clothes: ClothesWithBrand?,
    @Relation(
        entity = Shoes::class,
        parentColumn = "ShoesId",
        entityColumn = "Id"
    )
    val shoes: ShoesWithBrand?
) : SplatnetTransformer<SalmonRun> {

    override fun toSplatnet(context: Context): SalmonRun {

        val stage: SalmonRunStage? = salmonRunStage?.toSplatnet()

        val availableWeapons: List<SalmonRunWeapon>? = if (weapons != null) {
            listOf(
                weapons[0].toSplatnet().toSalmonRunWeapon(),
                weapons[1].toSplatnet().toSalmonRunWeapon(),
                weapons[2].toSplatnet().toSalmonRunWeapon(),
                weapons[3].toSplatnet().toSalmonRunWeapon()
            )
        } else {
            null
        }

        val gear: Gear? = headgear?.toSplatnet(context)
            ?: (clothes?.toSplatnet(context)
                ?: shoes?.toSplatnet(context))

        return salmonRun.toSplatnet(stage, availableWeapons, gear)
    }
}
