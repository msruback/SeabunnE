package com.beryl.seabunne.data.splatnet2.userInfo

import android.os.Parcelable
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("frequent_skill") val preferredSkill: Skill
) : Parcelable {

    private fun toRoom(): BrandEntity = BrandEntity(id, name, image, preferredSkill.id)

    fun stow(database: SplatnetDatabase) {
        database.skillDao().insertAll(preferredSkill)
        database.brandDao().insertAll(toRoom())
    }
}
