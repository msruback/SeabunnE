package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeapons
import com.beryl.seabunne.data.splatnet2.userInfo.Gear
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalmonRun(
    @SerializedName("stage") val stage: SalmonRunStage? = null,
    @SerializedName("weapons") val weapons: List<SalmonRunWeapon>? = null,
    @SerializedName("start_time") val start: Long,
    @SerializedName("end_time") val end: Long,
    val gear: Gear? = null
) : Parcelable {
    private fun toRoom(): SalmonRunEntity {
        return if (stage != null) {
            SalmonRunEntity(stage.toRoom().id, start, end)
        } else {
            SalmonRunEntity(-1, start, end)
        }
    }

    fun stow(database: SplatnetDatabase) {
        stage?.stow(database)
        if (!database.salmonRunDao().contains(start)) {
            weapons?.forEach {
                it.weapon.stow(database, true)
                database.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(start, it.id))
            }
            if (gear != null) {
                //TODO insert gear
            }
            database.salmonRunDao().insertAll(toRoom())
        } else if (stage != null && weapons != null && database.salmonRunDao()
                .containsOnlyTimeInfo(start)
        ) {
            weapons.forEach {
                it.weapon.stow(database, true)
                database.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(start, it.id))
            }
            if (gear != null) {
                //TODO insert gear
            }
            database.salmonRunDao().updateAll(toRoom())
        } else if (gear != null) {
            //TODO insert gear
            database.salmonRunDao().updateAll(toRoom())
        }
    }
}
