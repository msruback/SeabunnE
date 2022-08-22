package com.beryl.seabunne

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeaponDatabaseTests : DatabaseTest() {

    @Test
    fun test_specialInsert() {
        val special = Special(0, "Test Special", "url")

        splatnetDatabase.specialDao().insertAll(special)

        val storedSpecial = splatnetDatabase.specialDao().selectAll()[0]

        Assert.assertEquals(special.id, storedSpecial.id)
        Assert.assertEquals(special.name, storedSpecial.name)
        Assert.assertEquals(special.image, storedSpecial.image)
    }

    @Test
    fun test_subInsert() {
        val sub = Sub(0, "Test Sub", "url")

        splatnetDatabase.subDao().insertAll(sub)
    }
}