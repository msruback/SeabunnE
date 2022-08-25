package com.beryl.seabunne.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill

@Dao
interface SkillDao {

    @Query("SELECT COUNT() FROM Skills")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg skills: Skill)

    @Query("SELECT * FROM Skills WHERE Id = :skillId")
    fun select(skillId: Int): Skill

    @Query("SELECT * FROM SKILLS")
    fun selectAll(): List<Skill>
}
