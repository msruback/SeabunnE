package com.beryl.seabunne.data.splatnet2

import android.content.Context
import android.os.Parcelable
import com.beryl.seabunne.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class KeyName(val key: String, val name: String) : Parcelable {

    constructor(key: String, context: Context) : this(
        key, when (key) {
            "turf_war" -> context.getString(R.string.turfWar)
            "rainmaker" -> context.getString(R.string.rainmaker)
            "tower_control" -> context.getString(R.string.towerControl)
            "splat_zones" -> context.getString(R.string.splatzone)
            "clam_blitz" -> context.getString(R.string.clamBlitz)
            "low" -> context.getString(R.string.lowTide)
            "normal" -> context.getString(R.string.normalTide)
            "high" -> context.getString(R.string.highTide)
            "cohock-charge" -> context.getString(R.string.cohockCharge)
            "fog" -> context.getString(R.string.fog)
            "griller" -> context.getString(R.string.griller)
            "rush" -> context.getString(R.string.rush)
            "the-mothership" -> context.getString(R.string.theMothership)
            "goldie-seeking" -> context.getString(R.string.goldieSeeking)
            "regular" -> context.getString(R.string.regular)
            "gachi" -> context.getString(R.string.gachi)
            "league" -> context.getString(R.string.league)
            "league_pair" -> context.getString(R.string.leaguePair)
            "league_team" -> context.getString(R.string.leagueTeam)
            "10_x_match" -> context.getString(R.string.tenTimes)
            "100_x_match" -> context.getString(R.string.hundredTimes)
            else -> ""
        }
    )
}