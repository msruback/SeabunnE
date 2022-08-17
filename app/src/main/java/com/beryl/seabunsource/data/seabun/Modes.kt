package com.beryl.seabunsource.data.seabun

import com.beryl.seabunsource.R

object Modes {
    val regular =
        Mode(R.string.turfWar, R.color.turfPrimary, R.color.turfSecondary, R.color.turfTertiary)
    val gachi =
        Mode(R.string.gachi, R.color.gachiPrimary, R.color.gachiSecondary, R.color.gachiTertiary)
    val league = Mode(
        R.string.league,
        R.color.leaguePrimary,
        R.color.leagueSecondary,
        R.color.leagueTertiary
    )
    val salmonRun = Mode(
        R.string.salmonrun,
        R.color.salmonPrimary,
        R.color.gachiPrimary,
        R.color.salmonTertiary
    )
}