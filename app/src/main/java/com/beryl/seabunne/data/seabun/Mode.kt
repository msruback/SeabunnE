package com.beryl.seabunne.data.seabun

import android.content.Context

data class Mode(
    val nameResource: Int,
    val primaryColorResource: Int,
    val secondaryColorResource: Int,
    val tertiaryColorResource: Int
) {

    fun getName(context: Context): String {
        return context.getString(nameResource)
    }

    fun getPrimaryColor(context: Context): Int {
        return context.getColor(primaryColorResource)
    }

    fun getSecondaryColor(context: Context): Int {
        return context.getColor(secondaryColorResource)
    }

    fun getTertiaryColor(context: Context): Int {
        return context.getColor(tertiaryColorResource)
    }
}