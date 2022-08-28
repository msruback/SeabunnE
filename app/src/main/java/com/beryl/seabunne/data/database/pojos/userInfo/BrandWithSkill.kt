package com.beryl.seabunne.data.database.pojos.userInfo

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.beryl.seabunne.data.database.SplatnetTransformer
import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.splatnet2.userInfo.Brand
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill

data class BrandWithSkill(
    @Embedded val brand: BrandEntity,
    @Relation(
        entity = Skill::class,
        parentColumn = "SkillId",
        entityColumn = "Id"
    ) val skill: Skill?
) : SplatnetTransformer<Brand> {

    override fun toSplatnet(context: Context): Brand = brand.toSplatnet(skill)

}
