package com.beryl.seabunne.data.splatnet2.userInfo.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Skills")
data class Skill(
    @PrimaryKey @SerializedName("id") @ColumnInfo(name = "Id") val id: Int,
    @SerializedName("name") @ColumnInfo(name = "Name") val name: String,
    @SerializedName("image") @ColumnInfo(name = "Image") val image: String
) : Parcelable