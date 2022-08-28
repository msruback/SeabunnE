package com.beryl.seabunne.data.database.pojos.userInfo

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.beryl.seabunne.data.splatnet2.userInfo.Gear

data class ShoesWithBrand(
    @Embedded val shoes: Shoes,
    @Relation(
        entity = BrandEntity::class,
        parentColumn = "BrandId",
        entityColumn = "Id"
    ) val brand: BrandWithSkill
) : SplatnetTransformer<Gear> {

    override fun toSplatnet(context: Context): Gear = shoes.toSplatnet(brand.toSplatnet(context))

}