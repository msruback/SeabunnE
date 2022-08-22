package com.beryl.seabunne.data.database.dao

import androidx.room.*
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.database.entities.userInfo.WeaponWithSpecialAndSub

@Dao
interface WeaponDao {

    @Query("SELECT EXISTS(SELECT * FROM Weapons WHERE Id = :weaponId)")
    fun contains(weaponId: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM Weapons WHERE Id = :weaponId AND SpecialId = -1 AND SubId = -1)")
    fun containsOnlySalmonInfo(weaponId: Int): Boolean

    @Query("SELECT SalmonRun FROM Weapons WHERE Id = :weaponId")
    fun isSalmonRunWeapon(weaponId: Int): Boolean

    @Query("SELECT * FROM Weapons WHERE Id = :weaponId")
    fun getWeaponEntity(weaponId: Int): WeaponEntity

    @Transaction
    @Query("SELECT * FROM Weapons WHERE SalmonRun")
    fun getAllSalmonRunWeapons(): List<WeaponWithSpecialAndSub>

    @Insert
    fun insertAll(vararg weapons: WeaponEntity)

    @Update
    fun updateAll(vararg weapons: WeaponEntity)
}
