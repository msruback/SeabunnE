package com.beryl.seabunne.data.database.pojos.salmonRun

import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeaponEntity
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon

data class SalmonRunWeaponPojo(
    @Embedded val salmonRunWeaponEntity: SalmonRunWeaponEntity,
    @Relation(
        parentColumn = "WeaponId",
        entityColumn = "Id"
    )
    val weapon: WeaponEntity? = null
) {

    fun toSplatnet(): SalmonRunWeapon {
        return when (salmonRunWeaponEntity.weaponType) {
            "mystery" -> SalmonRunWeapon(-1)
            "grizz" -> SalmonRunWeapon(-2)
            else -> SalmonRunWeapon(weapon!!.id, weapon.toSplatnet())
        }
    }
}