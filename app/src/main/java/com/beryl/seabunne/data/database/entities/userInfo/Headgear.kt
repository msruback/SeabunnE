package com.beryl.seabunne.data.database.entities.userInfo

import androidx.room.*
import com.beryl.seabunne.data.splatnet2.userInfo.Brand
import com.beryl.seabunne.data.splatnet2.userInfo.Gear

@Entity(
    tableName = "Headgear",
    indices = [
        Index(value = ["BrandId"], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = ["Id"],
            childColumns = ["BrandId"]
        )
    ]
)
data class Headgear(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Kind") val kind: String,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Rarity") val rarity: Int,
    @ColumnInfo(name = "Image") val image: String,
    @ColumnInfo(name = "BrandId") val brand: Int
) {

    fun toSplatnet(brand: Brand): Gear = Gear(id, kind, name, rarity, image, brand)

}