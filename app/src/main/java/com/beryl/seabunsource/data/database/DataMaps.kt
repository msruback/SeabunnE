package com.beryl.seabunsource.data.database

import android.content.Context

fun <T, V : SplatnetTransformer<T>> listMap(oldList: List<V>, context: Context): List<T> {
    val newList: MutableList<T> = mutableListOf()
    oldList.forEach { flatItem ->
        newList.add(flatItem.toSplatnet(context))
    }
    return newList.toList()
}