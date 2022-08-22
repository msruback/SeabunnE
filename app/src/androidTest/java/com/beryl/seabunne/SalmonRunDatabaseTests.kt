package com.beryl.seabunne

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SalmonRunDatabaseTests : DatabaseTest() {

    @Test
    fun test_salmonRunStageInsert() {
        val salmonStage = SalmonRunStageEntity(0, "Test Stage", "url")

        splatnetDatabase.salmonRunStageDao().insertAll(salmonStage)

        val storedStage = splatnetDatabase.salmonRunStageDao().selectAll()[0]

        Assert.assertEquals(salmonStage.id, storedStage.id)
        Assert.assertEquals(salmonStage.name, storedStage.name)
        Assert.assertEquals(salmonStage.image, storedStage.image)
    }

    @Test
    fun test_salmonRunStageStow() {
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")

        salmonStage.stow(splatnetDatabase)

        val storedStage = splatnetDatabase.salmonRunStageDao().selectAll()[0]

        Assert.assertEquals(0, storedStage.id)
        Assert.assertEquals(salmonStage.name, storedStage.name)
        Assert.assertEquals(salmonStage.image, storedStage.image)
    }

    @Test
    fun test_salmonRunInsert() {

    }
}