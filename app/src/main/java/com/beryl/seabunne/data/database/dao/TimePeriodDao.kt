package com.beryl.seabunne.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.beryl.seabunne.data.database.entities.battles.TimePeriodAndStages
import com.beryl.seabunne.data.database.entities.battles.TimePeriodEntity

@Dao
interface TimePeriodDao {

    @Query("SELECT EXISTS(SELECT * FROM TimePeriods WHERE Id = :timePeriodId)")
    fun contains(timePeriodId: Int): Boolean

    @Query("SELECT EXISTS(SELECT Id FROM TimePeriods WHERE EndTime < :currentTime)")
    fun containsExpiredTimePeriods(currentTime: Long): Boolean

    @Query("SELECT COUNT() FROM TimePeriods WHERE GameMode LIKE 'regular'")
    fun countRegular(): Int

    @Query("DELETE FROM TimePeriods WHERE EndTime < :currentTime")
    fun deleteExpiredTimePeriods(currentTime: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg timePeriodData: TimePeriodEntity)

    @Query("SELECT EXISTS(SELECT * FROM TimePeriods WHERE Id = :timePeriodId AND StartTime = :timePeriodTime)")
    fun timePeriodNotExpired(timePeriodId: Int, timePeriodTime: Long): Boolean

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'regular' ORDER BY StartTime")
    fun selectRegular(): LiveData<List<TimePeriodAndStages>>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'gachi' ORDER BY StartTime")
    fun selectGachi(): LiveData<List<TimePeriodAndStages>>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'league' ORDER BY StartTime")
    fun selectLeague(): LiveData<List<TimePeriodAndStages>>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'regular' ORDER BY StartTime")
    fun selectRegularTest(): List<TimePeriodAndStages>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'gachi' ORDER BY StartTime")
    fun selectGachiTest(): List<TimePeriodAndStages>

    @Transaction
    @Query("SELECT * FROM TimePeriods WHERE GameMode LIKE 'league' ORDER BY StartTime")
    fun selectLeagueTest(): List<TimePeriodAndStages>
}
