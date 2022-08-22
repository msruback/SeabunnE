package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity

@Dao
interface SalmonRunStageDao {

    @Query("SELECT * FROM SalmonRunStages")
    fun selectAll(): List<SalmonRunStageEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg salmonStages: SalmonRunStageEntity)
}
