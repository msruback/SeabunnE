package com.beryl.seabunne.data.database.entities.userInfo

import androidx.room.*
import com.beryl.seabunne.data.splatnet2.userInfo.Brand
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill

@Entity(
    tableName = "Brands",
    indices = [
        Index(
            value = ["SkillId"],
            unique = false
        )
    ],
    foreignKeys = [
        ForeignKey(
            entity = Skill::class,
            parentColumns = ["Id"],
            childColumns = ["SkillId"]
        )
    ]
)
data class BrandEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Image") val image: String,
    @ColumnInfo(name = "SkillId") val skill: Int
) {

    fun toSplatnet(skill: Skill): Brand = Brand(id, name, image, skill)
}