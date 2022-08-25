package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeapons

@Dao
interface SalmonRunWeaponsDao {

    @Query("SELECT COUNT() FROM SalmonRunWeapons")
    fun count(): Int

    @Insert
    fun insertAll(vararg salmonRunWeapons: SalmonRunWeapons)

}
