package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GrizzCoListing(
    @SerializedName("reward_gear") val rewardGear: RewardGear
) : Parcelable