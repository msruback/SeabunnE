package com.beryl.seabunsource.data.splatnet2.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Stage(
    @PrimaryKey @SerializedName("id") @ColumnInfo(name = "Id") val id: Int,
    @SerializedName("image") @ColumnInfo(name = "Image") val image: String,
    @SerializedName("name") @ColumnInfo(name = "Name") val name: String
) : Parcelable