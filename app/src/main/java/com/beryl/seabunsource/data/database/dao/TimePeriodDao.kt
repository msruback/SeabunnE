package com.beryl.seabunsource.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.beryl.seabunsource.data.database.entities.TimePeriodAndStages
import com.beryl.seabunsource.data.database.entities.TimePeriodEntity

@Dao
interface TimePeriodDao {

    @Query("SELECT COUNT() FROM TimePeriods")
    fun count(): Int

    @Query("SELECT EXISTS(SELECT * FROM TimePeriods WHERE Id = :timePeriodId)")
    fun contains(timePeriodId: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM TimePeriods WHERE Id = :timePeriodId AND StartTime = :timePeriodTime)")
    fun timePeriodNotExpired(timePeriodId: Int, timePeriodTime: Long): Boolean

    @Query("SELECT EXISTS(SELECT Id FROM TimePeriods WHERE EndTime < :currentTime)")
    fun containsExpiredTimePeriods(currentTime: Long): Boolean

    @Query("DELETE FROM TimePeriods WHERE EndTime < :currentTime")
    fun deleteExpiredTimePeriods(currentTime: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg timePeriodData: TimePeriodEntity)

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'regular' ORDER BY StartTime")
    fun getRegular(): LiveData<List<TimePeriodAndStages>>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'gachi' ORDER BY StartTime")
    fun getGachi(): LiveData<List<TimePeriodAndStages>>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'league' ORDER BY StartTime")
    fun getLeague(): LiveData<List<TimePeriodAndStages>>
}