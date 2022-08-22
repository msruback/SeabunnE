package com.beryl.seabunne.data.database.dao

import androidx.room.*
import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.database.entities.userInfo.BrandWithSkill

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg brands: BrandEntity)

    @Transaction
    @Query("SELECT * FROM Brands")
    fun selectAll(): List<BrandWithSkill>
}
