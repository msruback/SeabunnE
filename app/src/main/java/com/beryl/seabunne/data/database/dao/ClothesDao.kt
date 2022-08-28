package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.beryl.seabunne.data.database.entities.userInfo.Clothes
import com.beryl.seabunne.data.database.pojos.userInfo.ClothesWithBrand

@Dao
interface ClothesDao {

    @Query("SELECT COUNT() FROM Clothes")
    fun count(): Int

    @Insert
    fun insertAll(vararg clothes: Clothes)

    @Transaction
    @Query("SELECT * FROM Clothes")
    fun selectAll(): List<ClothesWithBrand>
}
