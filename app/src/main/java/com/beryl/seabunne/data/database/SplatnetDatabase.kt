package com.beryl.seabunne.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beryl.seabunne.data.database.dao.*
import com.beryl.seabunne.data.database.entities.battles.TimePeriodEntity
import com.beryl.seabunne.data.database.entities.battles.TimePeriodStages
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeapons
import com.beryl.seabunne.data.database.entities.userInfo.*
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub

@Database(
    entities = [BrandEntity::class, Clothes::class, Headgear::class, SalmonRunEntity::class,
        SalmonRunStageEntity::class, SalmonRunWeapons::class, Shoes::class, Skill::class,
        Special::class, Stage::class, Sub::class, TimePeriodEntity::class, TimePeriodStages::class,
        WeaponEntity::class],
    version = 1,
    exportSchema = true
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

    abstract fun brandDao(): BrandDao

    abstract fun clothesDao(): ClothesDao

    abstract fun headgearDao(): HeadgearDao

    abstract fun salmonRunWeaponsDao(): SalmonRunWeaponsDao

    abstract fun salmonRunDao(): SalmonRunDao

    abstract fun salmonRunStageDao(): SalmonRunStageDao

    abstract fun shoesDao(): ShoesDao

    abstract fun skillDao(): SkillDao

    abstract fun specialDao(): SpecialDao

    abstract fun stageDao(): StageDao

    abstract fun subDao(): SubDao

    abstract fun timePeriodDao(): TimePeriodDao

    abstract fun timePeriodStagesDao(): TimePeriodStagesDao

    abstract fun weaponDao(): WeaponDao

}