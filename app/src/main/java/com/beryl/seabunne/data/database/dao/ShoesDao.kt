package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.beryl.seabunne.data.database.entities.userInfo.ShoesWithBrand

@Dao
interface ShoesDao {

    @Query("SELECT COUNT() FROM Shoes")
    fun count(): Int

    @Insert
    fun insertAll(vararg shoes: Shoes)

    @Transaction
    @Query("SELECT * FROM Shoes")
    fun selectAll(): List<ShoesWithBrand>
}