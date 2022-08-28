package com.beryl.seabunne.data.database.pojos.salmonRun

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeaponEntity
import com.beryl.seabunne.data.database.entities.userInfo.Clothes
import com.beryl.seabunne.data.database.entities.userInfo.Headgear
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.beryl.seabunne.data.database.pojos.userInfo.ClothesWithBrand
import com.beryl.seabunne.data.database.pojos.userInfo.HeadgearWithBrand
import com.beryl.seabunne.data.database.pojos.userInfo.ShoesWithBrand
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
        entity = SalmonRunWeaponEntity::class,
        parentColumn = "StartTime",
        entityColumn = "SalmonRunId"
    )
    val weapons: List<SalmonRunWeaponPojo>?,
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

        val availableWeapons: List<SalmonRunWeapon>? = if (weapons?.size == 4) {
            listOf(
                weapons[0].toSplatnet(),
                weapons[1].toSplatnet(),
                weapons[2].toSplatnet(),
                weapons[3].toSplatnet()
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
