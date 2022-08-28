package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeaponEntity
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class SalmonRunWeapon(
    @SerializedName("id") val id: Int,
    @SerializedName("weapon") val weapon: Weapon? = null
) : Parcelable {

    @IgnoredOnParcel
    val weaponType: String
        get() = when (id) {
            -1 -> "mystery"
            -2 -> "grizz"
            else -> "normal"
        }

    fun toRoom(salmonId: Long, position: Int): SalmonRunWeaponEntity =
        SalmonRunWeaponEntity(salmonId, position, weaponType, weapon?.id)

}
