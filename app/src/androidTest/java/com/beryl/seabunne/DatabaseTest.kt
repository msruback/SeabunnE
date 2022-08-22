package com.beryl.seabunne

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.beryl.seabunne.data.database.SplatnetDatabase
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {

    lateinit var splatnetDatabase: SplatnetDatabase

    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        splatnetDatabase = Room.inMemoryDatabaseBuilder(context, SplatnetDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @After
    fun teardownDatabase() {
        splatnetDatabase.close()
    }
}