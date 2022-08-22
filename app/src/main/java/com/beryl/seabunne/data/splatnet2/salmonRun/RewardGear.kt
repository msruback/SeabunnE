package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.beryl.seabunne.data.splatnet2.userInfo.Gear
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RewardGear(
    @SerializedName("available_time") val available: Long,
    @SerializedName("gear") val gear: Gear
) : Parcelable