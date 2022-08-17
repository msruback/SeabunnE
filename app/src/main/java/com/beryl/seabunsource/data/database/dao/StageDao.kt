package com.beryl.seabunsource.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunsource.data.splatnet2.entities.Stage

@Dao
interface StageDao {

    @Query("SELECT EXISTS (SELECT * FROM Stage WHERE Id LIKE :stageId)")
    fun contains(stageId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg stages: Stage)

    @Query("SELECT * FROM Stage")
    fun getAll(): List<Stage>

}