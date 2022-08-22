package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity

@Dao
interface SalmonRunDao {

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime = :salmonId)")
    fun contains(salmonId: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime = :salmonId AND StageId = -1)")
    fun containsOnlyTimeInfo(salmonId: Long): Boolean

    @Insert
    fun insertAll(vararg salmonRuns: SalmonRunEntity)

    @Update
    fun updateAll(vararg salmonRuns: SalmonRunEntity)
}
