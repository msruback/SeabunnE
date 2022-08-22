package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.beryl.seabunne.data.database.entities.battles.TimePeriodStages

@Dao
interface TimePeriodStagesDao {

    @Insert
    fun insertAll(vararg timePeriodStages: TimePeriodStages)
}
