package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.splatnet2.battles.entities.Stage

@Dao
interface StageDao {

    @Query("SELECT EXISTS (SELECT * FROM Stages WHERE Id LIKE :stageId)")
    fun contains(stageId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg stages: Stage)

    @Query("SELECT * FROM Stages")
    fun getAll(): List<Stage>

}
