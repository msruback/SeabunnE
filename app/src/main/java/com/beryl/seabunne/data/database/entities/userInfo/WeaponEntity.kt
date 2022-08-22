package com.beryl.seabunne.data.database.entities.userInfo

import androidx.room.*
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub

@Entity(
    tableName = "Weapons",
    indices = [
        Index(value = ["SpecialId"], unique = false),
        Index(value = ["SubId"], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Special::class,
            parentColumns = ["Id"],
            childColumns = ["SpecialId"]
        ),
        ForeignKey(
            entity = Sub::class,
            parentColumns = ["Id"],
            childColumns = ["SubId"]
        )
    ]
)
data class WeaponEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Image") val image: String,
    @ColumnInfo(name = "SpecialId") val special: Int,
    @ColumnInfo(name = "SubId") val sub: Int,
    @ColumnInfo(name = "SalmonRun") var isSalmonRun: Boolean
) {

    fun toSplatnet(special: Special, sub: Sub): Weapon {
        return Weapon(id, name, image, special, sub)
    }
}
