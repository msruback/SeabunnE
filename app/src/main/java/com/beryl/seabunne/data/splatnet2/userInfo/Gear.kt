package com.beryl.seabunne.data.splatnet2.userInfo

import android.os.Parcelable
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.entities.userInfo.Clothes
import com.beryl.seabunne.data.database.entities.userInfo.Headgear
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gear(
    @SerializedName("id") val id: Int,
    @SerializedName("kind") val kind: String,
    @SerializedName("name") val name: String,
    @SerializedName("rarity") val rarity: Int,
    @SerializedName("image") val image: String,
    @SerializedName("brand") val brand: Brand
) : Parcelable {

    fun toRoomHeadgear(): Headgear = Headgear(id, kind, name, rarity, image, brand.id)

    fun toRoomClothes(): Clothes = Clothes(id, kind, name, rarity, image, brand.id)

    fun toRoomShoes(): Shoes = Shoes(id, kind, name, rarity, image, brand.id)

    fun stow(database: SplatnetDatabase) {
        brand.stow(database)
        when (kind) {
            "head" -> database.headgearDao().insertAll(toRoomHeadgear())
            "clothes" -> database.clothesDao().insertAll(toRoomClothes())
            "shoes" -> database.shoesDao().insertAll(toRoomShoes())
        }
    }

}
