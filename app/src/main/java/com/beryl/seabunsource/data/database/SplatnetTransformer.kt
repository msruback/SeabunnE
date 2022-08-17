package com.beryl.seabunsource.data.database

import android.content.Context

interface SplatnetTransformer<T> {
    fun toSplatnet(context: Context): T
}