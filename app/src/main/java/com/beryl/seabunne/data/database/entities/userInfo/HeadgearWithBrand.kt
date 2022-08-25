package com.beryl.seabunne.data.database.entities.userInfo

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.splatnet2.userInfo.Gear

data class HeadgearWithBrand(
    @Embedded val headgear: Headgear,
    @Relation(
        entity = BrandEntity::class,
        parentColumn = "BrandId",
        entityColumn = "Id"
    ) val brand: BrandWithSkill
) : SplatnetTransformer<Gear> {

    override fun toSplatnet(context: Context): Gear = headgear.toSplatnet(brand.toSplatnet(context))

}