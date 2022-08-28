package com.beryl.seabunne.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity
import com.beryl.seabunne.data.database.pojos.salmonRun.SalmonRunWithStageAndWeaponsAndGear

@Dao
interface SalmonRunDao {

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime = :salmonId)")
    fun contains(salmonId: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE EndTime < :currentTime)")
    fun containsExpiredRuns(currentTime: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime < :currentTime AND EndTime > :currentTime AND HeadgearId IS NULL AND ClothesId IS NULL AND ShoesId IS NULL)")
    fun containsCurrentRunWithoutGear(currentTime: Long): Boolean

    @Query("SELECT COUNT() FROM SalmonRuns")
    fun count(): Int

    @Query("DELETE FROM SalmonRuns WHERE EndTime < :currentTime")
    fun deleteExpiredRuns(currentTime: Long)

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime = :salmonId AND StageId IS NULL)")
    fun lacksStageAndWeapons(salmonId: Long): Boolean

    @Query("SELECT EXISTS(SELECT * FROM SalmonRuns WHERE StartTime = :salmonId AND HeadgearId IS NULL AND ClothesId IS NULL AND ShoesId IS NULL)")
    fun lacksGear(salmonId: Long): Boolean

    @Insert
    fun insertAll(vararg salmonRuns: SalmonRunEntity)

    @Transaction
    @Query("SELECT * FROM SalmonRuns")
    fun selectAll(): LiveData<List<SalmonRunWithStageAndWeaponsAndGear>>

    @Transaction
    @Query("SELECT * FROM SalmonRuns")
    fun selectAllTest(): List<SalmonRunWithStageAndWeaponsAndGear>

    @Transaction
    @Query("SELECT * FROM SalmonRuns WHERE StartTime < :currentTime AND EndTime > :currentTime")
    fun selectCurrent(currentTime: Long): SalmonRunWithStageAndWeaponsAndGear

    @Update
    fun updateAll(vararg salmonRuns: SalmonRunEntity)
}
