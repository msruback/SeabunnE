package com.beryl.seabunne.data.database.dao

import androidx.room.*
import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.database.pojos.userInfo.BrandWithSkill

@Dao
interface BrandDao {

    @Query("SELECT COUNT() FROM Brands")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg brands: BrandEntity)

    @Query("SELECT * FROM Brands WHERE Id = :brandId")
    fun select(brandId: Int): BrandEntity

    @Transaction
    @Query("SELECT * FROM Brands")
    fun selectAll(): List<BrandWithSkill>
}
