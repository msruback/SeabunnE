package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special

@Dao
interface SpecialDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg specials: Special)

    @Query("SELECT * FROM Specials WHERE Id = :specialId")
    fun select(specialId: Int): Special

    @Query("SELECT * FROM Specials")
    fun selectAll(): List<Special>
}
