package com.beryl.seabunsource.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.beryl.seabunsource.data.database.entities.TimePeriodStages

@Dao
interface TimePeriodStagesDao {

    @Insert
    fun insertAll(vararg timePeriodStages: TimePeriodStages)
}