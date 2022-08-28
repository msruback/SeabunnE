package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.beryl.seabunne.data.database.entities.userInfo.Headgear
import com.beryl.seabunne.data.database.pojos.userInfo.HeadgearWithBrand

@Dao
interface HeadgearDao {

    @Query("SELECT COUNT() FROM Headgear")
    fun count(): Int

    @Insert
    fun insertAll(vararg headgear: Headgear)

    @Transaction
    @Query("SELECT * FROM Headgear")
    fun selectAll(): List<HeadgearWithBrand>
}
