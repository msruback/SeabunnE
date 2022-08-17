package com.beryl.seabunsource.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beryl.seabunsource.data.database.dao.StageDao
import com.beryl.seabunsource.data.database.dao.TimePeriodDao
import com.beryl.seabunsource.data.database.dao.TimePeriodStagesDao
import com.beryl.seabunsource.data.database.entities.TimePeriodEntity
import com.beryl.seabunsource.data.database.entities.TimePeriodStages
import com.beryl.seabunsource.data.splatnet2.entities.Stage

@Database(
    entities = [Stage::class, TimePeriodEntity::class, TimePeriodStages::class],
    version = 1,
    exportSchema = false
)
abstract class SplatnetDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: SplatnetDatabase? = null

        fun getDatabase(context: Context): SplatnetDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SplatnetDatabase::class.java,
                    "splatnet_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }

    abstract fun stageDao(): StageDao

    abstract fun timePeriodDao(): TimePeriodDao

    abstract fun timePeriodStagesDao(): TimePeriodStagesDao

}