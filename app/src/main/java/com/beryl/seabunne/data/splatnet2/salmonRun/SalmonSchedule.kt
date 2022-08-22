package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalmonSchedule(
    @SerializedName("details") val detailedShifts: List<SalmonRun>,
    @SerializedName("schedules") val times: List<SalmonRun>
) : Parcelable
