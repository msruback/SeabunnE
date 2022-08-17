package com.beryl.seabunsource.data.splatnet2

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedules(
    @SerializedName("regular") val regular: List<TimePeriod>,
    @SerializedName("gachi") val ranked: List<TimePeriod>,
    @SerializedName("league") val league: List<TimePeriod>
) : Parcelable