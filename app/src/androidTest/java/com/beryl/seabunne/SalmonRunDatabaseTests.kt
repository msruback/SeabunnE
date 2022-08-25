package com.beryl.seabunne

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunStageEntity
import com.beryl.seabunne.data.database.entities.salmonRun.SalmonRunWeapons
import com.beryl.seabunne.data.database.entities.userInfo.*
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRun
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunStage
import com.beryl.seabunne.data.splatnet2.userInfo.Brand
import com.beryl.seabunne.data.splatnet2.userInfo.Gear
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SalmonRunDatabaseTests : DatabaseTest() {

    @Test
    fun test_salmonRunStageInsert() {
        val salmonStage = SalmonRunStageEntity(0, "Test Stage", "url")

        splatnetDatabase.salmonRunStageDao().insertAll(salmonStage)

        val storedStage = splatnetDatabase.salmonRunStageDao().selectAll()[0]

        Assert.assertEquals(salmonStage, storedStage)
    }

    @Test
    fun test_salmonRunStageStow() {
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")

        salmonStage.stow(splatnetDatabase)

        val storedStage = splatnetDatabase.salmonRunStageDao().selectAll()[0]

        Assert.assertEquals(salmonStage.toRoom(), storedStage)
    }

    @Test
    fun test_salmonRunInsert() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStageEntity(0, "Test Stage", "url")
        val salmonRun = SalmonRunEntity(startTime, endTime, 0)

        val weapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        splatnetDatabase.salmonRunStageDao().insertAll(salmonStage)

        splatnetDatabase.salmonRunDao().insertAll(salmonRun)
        weapons.forEach {
            splatnetDatabase.weaponDao().insertAll(it)
            splatnetDatabase.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(startTime, it.id))
        }

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        Assert.assertEquals(salmonRun, storedRun.salmonRun)
        Assert.assertEquals(salmonStage, storedRun.salmonRunStage)
        Assert.assertEquals(weapons, storedRun.weapons)
    }

    @Test
    fun test_basicSalmonStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonRun = SalmonRun(startTime, endTime)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
    }

    @Test
    fun test_extendedSalmonStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )

        val salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
    }

    @Test
    fun test_extendedSalmonUpdated() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )

        var salmonRun = SalmonRun(startTime, endTime)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)

    }

    @Test
    fun test_currentSalmonHeadgearStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "head", "hat", 1, "url", brand)

        val salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomHeadgear(), storedRun.headgear!!.headgear)
        Assert.assertEquals(brand.toRoom(), storedRun.headgear!!.brand.brand)
        Assert.assertEquals(skill, storedRun.headgear!!.brand.skill)
    }

    @Test
    fun test_currentSalmonHeadgearUpdate() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "head", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomHeadgear(), storedRun.headgear!!.headgear)
        Assert.assertEquals(brand.toRoom(), storedRun.headgear!!.brand.brand)
        Assert.assertEquals(skill, storedRun.headgear!!.brand.skill)
    }

    @Test
    fun test_currentSalmonHeadgearUpdateWithStageAndWeapons() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "head", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomHeadgear(), storedRun.headgear!!.headgear)
        Assert.assertEquals(brand.toRoom(), storedRun.headgear!!.brand.brand)
        Assert.assertEquals(skill, storedRun.headgear!!.brand.skill)
    }

    @Test
    fun test_currentSalmonClothesStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "clothes", "hat", 1, "url", brand)

        val salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomClothes(), storedRun.clothes!!.clothes)
        Assert.assertEquals(brand.toRoom(), storedRun.clothes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.clothes!!.brand.skill)
    }

    @Test
    fun test_currentSalmonClothesUpdate() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "clothes", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]


        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomClothes(), storedRun.clothes!!.clothes)
        Assert.assertEquals(brand.toRoom(), storedRun.clothes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.clothes!!.brand.skill)
    }

    @Test
    fun test_currentSalmonClothesUpdateWithStageAndWeapons() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "clothes", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]


        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomClothes(), storedRun.clothes!!.clothes)
        Assert.assertEquals(brand.toRoom(), storedRun.clothes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.clothes!!.brand.skill)
    }

    @Test
    fun test_currentSalmonShoesStow() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "shoes", "hat", 1, "url", brand)

        val salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomShoes(), storedRun.shoes!!.shoes)
        Assert.assertEquals(brand.toRoom(), storedRun.shoes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.shoes!!.brand.skill)
    }

    @Test
    fun test_currentSalmonShoesUpdate() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "shoes", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomShoes(), storedRun.shoes!!.shoes)
        Assert.assertEquals(brand.toRoom(), storedRun.shoes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.shoes!!.brand.skill)
    }

    @Test
    fun test_currentSalmonShoesUpdateWithStageAndWeapons() {
        val startTime = Date().time / 1000
        val endTime = startTime + 7200
        val salmonStage = SalmonRunStage("Spawning Grounds", "url")
        val weapons = listOf(
            Weapon(0, "Test Weapon 1", "url").toSalmonRunWeapon(),
            Weapon(1, "Test Weapon 2", "url").toSalmonRunWeapon(),
            Weapon(2, "Test Weapon 3", "url").toSalmonRunWeapon(),
            Weapon(3, "Test Weapon 4", "url").toSalmonRunWeapon()
        )
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "shoes", "hat", 1, "url", brand)

        var salmonRun = SalmonRun(startTime, endTime)
        salmonRun.stow(splatnetDatabase)

        salmonRun = SalmonRun(startTime, endTime, salmonStage, weapons, gear)
        salmonRun.stow(splatnetDatabase)

        val storedRun = splatnetDatabase.salmonRunDao().selectAllTest()[0]

        val expectedWeapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        Assert.assertEquals(salmonRun.toRoom(), storedRun.salmonRun)
        Assert.assertEquals(salmonStage.toRoom(), storedRun.salmonRunStage)
        Assert.assertEquals(expectedWeapons, storedRun.weapons)
        Assert.assertEquals(gear.toRoomShoes(), storedRun.shoes!!.shoes)
        Assert.assertEquals(brand.toRoom(), storedRun.shoes!!.brand.brand)
        Assert.assertEquals(skill, storedRun.shoes!!.brand.skill)
    }

    @Test
    fun test_salmonRunExpired() {
        val currentTime = Date().time / 1000
        val startTime = currentTime - 10800
        val endTime = startTime + 7200

        val salmonRun = SalmonRunEntity(startTime, endTime)

        splatnetDatabase.salmonRunDao().insertAll(salmonRun)

        Assert.assertTrue(splatnetDatabase.salmonRunDao().containsExpiredRuns(currentTime))
    }

    @Test
    fun test_salmonRunNotExpired() {
        val currentTime = Date().time / 1000
        val startTime = Date().time / 1000
        val endTime = startTime + 7200

        val salmonRun = SalmonRunEntity(startTime, endTime)

        splatnetDatabase.salmonRunDao().insertAll(salmonRun)

        Assert.assertFalse(splatnetDatabase.salmonRunDao().containsExpiredRuns(currentTime))
    }

    @Test
    fun test_salmonRunFullDelete() {
        val currentTime = Date().time / 1000
        val startTime = currentTime - 10800
        val endTime = startTime + 7200

        val salmonStage = SalmonRunStageEntity(0, "Test Stage", "url")
        val skill = Skill(0, "Skill", "url")
        val brand = BrandEntity(0, "Brand", "url", 0)
        val headgear = Headgear(0, "head", "Test Headgear", 0, "url", 0)
        val clothes = Clothes(0, "clothes", "Test Clothes", 0, "url", 0)
        val shoes = Shoes(0, "shoes", "Test Headgear", 0, "url", 0)

        val salmonRun = SalmonRunEntity(startTime, endTime, 0, 0, 0, 0)

        val weapons = listOf(
            WeaponEntity(0, "Test Weapon 1", "url", isSalmonRun = true),
            WeaponEntity(1, "Test Weapon 2", "url", isSalmonRun = true),
            WeaponEntity(2, "Test Weapon 3", "url", isSalmonRun = true),
            WeaponEntity(3, "Test Weapon 4", "url", isSalmonRun = true)
        )

        splatnetDatabase.skillDao().insertAll(skill)
        splatnetDatabase.brandDao().insertAll(brand)
        splatnetDatabase.headgearDao().insertAll(headgear)
        splatnetDatabase.clothesDao().insertAll(clothes)
        splatnetDatabase.shoesDao().insertAll(shoes)

        splatnetDatabase.salmonRunStageDao().insertAll(salmonStage)

        splatnetDatabase.salmonRunDao().insertAll(salmonRun)
        weapons.forEach {
            splatnetDatabase.weaponDao().insertAll(it)
            splatnetDatabase.salmonRunWeaponsDao().insertAll(SalmonRunWeapons(startTime, it.id))
        }

        splatnetDatabase.salmonRunDao().deleteExpiredRuns(currentTime)

        Assert.assertEquals(0, splatnetDatabase.salmonRunDao().count())
        Assert.assertEquals(0, splatnetDatabase.salmonRunWeaponsDao().count())
        Assert.assertEquals(4, splatnetDatabase.weaponDao().count())
        Assert.assertEquals(1, splatnetDatabase.salmonRunStageDao().count())
        Assert.assertEquals(1, splatnetDatabase.headgearDao().count())
        Assert.assertEquals(1, splatnetDatabase.clothesDao().count())
        Assert.assertEquals(1, splatnetDatabase.shoesDao().count())
        Assert.assertEquals(1, splatnetDatabase.brandDao().count())
        Assert.assertEquals(1, splatnetDatabase.skillDao().count())
    }
}
