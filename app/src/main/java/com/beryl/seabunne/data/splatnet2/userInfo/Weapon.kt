package com.beryl.seabunne.data.splatnet2.userInfo

import android.os.Parcelable
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weapon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("special") val special: Special? = null,
    @SerializedName("sub") val sub: Sub? = null
) : Parcelable {

    fun toSalmonRunWeapon(): SalmonRunWeapon = SalmonRunWeapon(id, this)

    private fun toRoom(isSalmonRun: Boolean): WeaponEntity {
        return if (special != null && sub != null) {
            WeaponEntity(id, name, image, special.id, sub.id, isSalmonRun)
        } else {
            WeaponEntity(id, name, image, -1, -1, isSalmonRun)
        }
    }

    fun stow(database: SplatnetDatabase, isSalmonRun: Boolean) {
        if (!database.weaponDao().contains(id)) {
            if (special != null && sub != null) {
                database.specialDao().insertAll(special)
                database.subDao().insertAll(sub)
            }
            database.weaponDao().insertAll(toRoom(isSalmonRun))
        } else if (database.weaponDao()
                .containsOnlySalmonInfo(id) && special != null && sub != null
        ) {
            database.specialDao().insertAll(special)
            database.subDao().insertAll(sub)
            database.weaponDao().updateAll(toRoom(true))
        } else if (!database.weaponDao().isSalmonRunWeapon(id) && isSalmonRun) {
            val toStow = database.weaponDao().getWeaponEntity(id)
            toStow.isSalmonRun = true
            database.weaponDao().updateAll(toStow)
        }
    }
}
