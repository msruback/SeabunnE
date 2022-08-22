package com.beryl.seabunne.data.splatnet2

import android.os.Parcelable
import com.beryl.seabunne.data.splatnet2.salmonRun.GrizzCoListing
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timeline(
    @SerializedName("unique_id") val uniqueID: String,
    @SerializedName("coop") val currentRun: GrizzCoListing
) : Parcelable