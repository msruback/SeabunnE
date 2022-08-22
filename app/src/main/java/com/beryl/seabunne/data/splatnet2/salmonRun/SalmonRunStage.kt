package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.beryl.seabunne.data.database.SplatnetDatabase
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalmonRunStage(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
) : Parcelable {
    fun toRoom(): SalmonRunStageEntity {
        val id = when (name) {
            "Spawning Grounds" -> 0
            "Marooner's Bay" -> 1
            "Lost Outpost" -> 2
            "Salmonid Smokeyard" -> 3
            "Ruins of Ark Polaris" -> 4
            else -> -1
        }
        return SalmonRunStageEntity(id, name, image)
    }

    fun stow(database: SplatnetDatabase) {
        database.salmonRunStageDao().insertAll(toRoom())
    }
}
