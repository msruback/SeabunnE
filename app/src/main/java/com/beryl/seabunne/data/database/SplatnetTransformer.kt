package com.beryl.seabunne.data.database

import android.content.Context

interface SplatnetTransformer<T> {
    fun toSplatnet(context: Context): T
}