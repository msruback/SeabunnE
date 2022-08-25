package com.beryl.seabunne

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beryl.seabunne.data.database.entities.battles.TimePeriodEntity
import com.beryl.seabunne.data.database.entities.battles.TimePeriodStages
import com.beryl.seabunne.data.splatnet2.KeyName
import com.beryl.seabunne.data.splatnet2.battles.TimePeriod
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class ScheduleDatabaseTests : DatabaseTest() {

    @Test
    fun test_timePeriodDatabaseInsert() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val cal = Calendar.getInstance()
        cal.time = Date(startTime * 1000)

        val id = cal.get(Calendar.HOUR_OF_DAY)
        val timePeriod = TimePeriodEntity(id, "regular", "turf_war", startTime, endTime)
        val stage1 = Stage(0, "Test Stage 1", "url")
        val stage2 = Stage(1, "Test Stage 2", "url")


        splatnetDatabase.stageDao().insertAll(stage1, stage2)
        splatnetDatabase.timePeriodDao().insertAll(timePeriod)
        splatnetDatabase.timePeriodStagesDao()
            .insertAll(TimePeriodStages(id, stage1.id), TimePeriodStages(id, stage2.id))

        val stowedTimePeriod = splatnetDatabase.timePeriodDao().selectRegularTest()[0]

        Assert.assertEquals(timePeriod, stowedTimePeriod.timePeriodData)
        Assert.assertEquals(stage1, stowedTimePeriod.stages[0])
        Assert.assertEquals(stage2, stowedTimePeriod.stages[1])

    }

    @Test
    fun test_timePeriodStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val stage1 = Stage(0, "Test Stage 1", "url")
        val stage2 = Stage(1, "Test Stage 2", "url")
        val timePeriod = TimePeriod(
            KeyName("regular", "Regular"), KeyName("turf_war", "Turf War"),
            stage1, stage2, startTime, endTime
        )

        timePeriod.stow(splatnetDatabase)

        val stowedTimePeriod = splatnetDatabase.timePeriodDao().selectRegularTest()[0]

        Assert.assertEquals(timePeriod.toRoom(), stowedTimePeriod.timePeriodData)
        Assert.assertEquals(stage1, stowedTimePeriod.stages[0])
        Assert.assertEquals(stage2, stowedTimePeriod.stages[1])
    }

    @Test
    fun test_stageDatabaseInsert() {
        val stage = Stage(0, "Test Stage 1", "url")

        splatnetDatabase.stageDao().insertAll(stage)

        val storedStage = splatnetDatabase.stageDao().selectAll()[0]

        Assert.assertEquals(stage, storedStage)
    }
}
