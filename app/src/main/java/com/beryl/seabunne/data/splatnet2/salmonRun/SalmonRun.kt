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
    @SerializedName("start_time") val start: Long,
    @SerializedName("end_time") val end: Long,
    @SerializedName("stage") val stage: SalmonRunStage? = null,
    @SerializedName("weapons") val weapons: List<SalmonRunWeapon>? = null,
    val gear: Gear? = null
) : Parcelable {

    fun toRoom(): SalmonRunEntity {

        var headgear: Int? = null
        var clothes: Int? = null
        var shoes: Int? = null

        when (gear?.kind) {
            "head" -> headgear = gear.id
            "clothes" -> clothes = gear.id
            "shoes" -> shoes = gear.id
        }

        return SalmonRunEntity(start, end, stage?.toRoom()?.id, headgear, clothes, shoes)
    }

    fun stow(database: SplatnetDatabase) {
        stage?.stow(database)
        if (!database.salmonRunDao().contains(start)) {
            stage?.stow(database)
            gear?.stow(database)
            database.salmonRunDao().insertAll(toRoom())

            weapons?.forEach {
                it.weapon.stow(database, true)
                database.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(start, it.id))
            }
        } else if (stage != null && weapons != null && database.salmonRunDao()
                .lacksStageAndWeapons(start)
        ) {
            stage.stow(database)
            gear?.stow(database)
            database.salmonRunDao().updateAll(toRoom())

            weapons.forEach {
                it.weapon.stow(database, true)
                database.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(start, it.id))
            }
        } else if (gear != null && database.salmonRunDao().lacksGear(start)) {
            gear.stow(database)
            database.salmonRunDao().updateAll(toRoom())
        }
    }
}
