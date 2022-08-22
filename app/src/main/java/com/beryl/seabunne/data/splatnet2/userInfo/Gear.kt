package com.beryl.seabunne.data.splatnet2.userInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gear(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("brand") val brand: Brand,
    @SerializedName("image") val image: String,
    @SerializedName("rarity") val rarity: Int,
    @SerializedName("kind") val kind: String
) : Parcelable
