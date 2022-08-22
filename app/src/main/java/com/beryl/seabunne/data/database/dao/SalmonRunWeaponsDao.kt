package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeapons

@Dao
interface SalmonRunWeaponsDao {

    @Insert
    fun insertAll(vararg salmonRunWeapons: SalmonRunWeapons)
}
