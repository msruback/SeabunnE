package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity

@Dao
interface SalmonRunStageDao {

    @Query("SELECT COUNT() FROM SalmonRunStages")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg salmonStages: SalmonRunStageEntity)

    @Query("SELECT * FROM SalmonRunStages WHERE Id = :salmonStageId")
    fun select(salmonStageId: Int): SalmonRunStageEntity

    @Query("SELECT * FROM SalmonRunStages")
    fun selectAll(): List<SalmonRunStageEntity>
}
