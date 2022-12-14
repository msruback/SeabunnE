package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub

@Dao
interface SubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg subs: Sub)

    @Query("SELECT * FROM Subs WHERE Id = :subId")
    fun select(subId: Int): Sub

    @Query("SELECT * FROM Subs")
    fun selectAll(): List<Sub>
}
