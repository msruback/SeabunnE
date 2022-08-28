package com.beryl.seabunne.data.database.dao

import androidx.room.*
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.database.pojos.userInfo.WeaponWithSpecialAndSub

@Dao
interface WeaponDao {

    @Query("SELECT EXISTS(SELECT * FROM Weapons WHERE Id = :weaponId)")
    fun contains(weaponId: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM Weapons WHERE Id = :weaponId AND SpecialId IS NULL AND SubId IS NULL)")
    fun containsOnlySalmonInfo(weaponId: Int): Boolean

    @Query("SELECT COUNT() FROM Weapons")
    fun count(): Int

    @Insert
    fun insertAll(vararg weapons: WeaponEntity)

    @Query("SELECT SalmonRun FROM Weapons WHERE Id = :weaponId")
    fun isSalmonRunWeapon(weaponId: Int): Boolean

    @Transaction
    @Query("SELECT * FROM Weapons")
    fun selectAll(): List<WeaponWithSpecialAndSub>

    @Transaction
    @Query("SELECT * FROM Weapons WHERE SalmonRun")
    fun selectAllSalmonRunWeapons(): List<WeaponWithSpecialAndSub>

    @Query("SELECT * FROM Weapons WHERE Id = :weaponId")
    fun selectWeaponEntity(weaponId: Int): WeaponEntity

    @Update
    fun updateAll(vararg weapons: WeaponEntity)
}
