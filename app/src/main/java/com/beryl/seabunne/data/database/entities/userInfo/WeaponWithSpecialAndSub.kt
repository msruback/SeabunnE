package com.beryl.seabunne.data.database.entities.userInfo

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub

data class WeaponWithSpecialAndSub(
    @Embedded val weapon: WeaponEntity,
    @Relation(
        parentColumn = "SpecialId",
        entityColumn = "Id"
    ) val special: Special?,
    @Relation(
        parentColumn = "SubId",
        entityColumn = "Id"
    ) val sub: Sub?
) : SplatnetTransformer<Weapon> {
    override fun toSplatnet(context: Context): Weapon = weapon.toSplatnet(special, sub)
}
