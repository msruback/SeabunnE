package com.beryl.seabunne

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Special
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Sub
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeaponDatabaseTests : DatabaseTest() {

    @Test
    fun test_specialInsert() {
        val special = Special(0, "Test Special", "url")

        splatnetDatabase.specialDao().insertAll(special)

        val storedSpecial = splatnetDatabase.specialDao().selectAll()[0]

        Assert.assertEquals(special, storedSpecial)
    }

    @Test
    fun test_subInsert() {
        val sub = Sub(0, "Test Sub", "url")

        splatnetDatabase.subDao().insertAll(sub)

        val storedSub = splatnetDatabase.subDao().selectAll()[0]

        Assert.assertEquals(sub, storedSub)
    }

    @Test
    fun test_weaponInsert() {
        val special = Special(0, "Test Special", "url")
        val sub = Sub(0, "Test Sub", "url")
        val weapon = WeaponEntity(0, "Test Weapon", "url", 0, 0, false)

        splatnetDatabase.specialDao().insertAll(special)
        splatnetDatabase.subDao().insertAll(sub)
        splatnetDatabase.weaponDao().insertAll(weapon)

        val storedWeapon = splatnetDatabase.weaponDao().selectAll()[0]

        Assert.assertEquals(weapon, storedWeapon.weapon)
        Assert.assertEquals(special, storedWeapon.special)
        Assert.assertEquals(sub, storedWeapon.sub)
    }

    @Test
    fun test_weaponStowNormal() {
        val special = Special(0, "Test Special", "url")
        val sub = Sub(0, "Test Sub", "url")
        val weapon = Weapon(0, "Test Weapon", "url", special, sub)

        weapon.stow(splatnetDatabase, false)

        val storedWeapon = splatnetDatabase.weaponDao().selectAll()[0]

        Assert.assertEquals(weapon.toRoom(false), storedWeapon.weapon)
        Assert.assertEquals(special, storedWeapon.special)
        Assert.assertEquals(sub, storedWeapon.sub)
    }

    @Test
    fun test_weaponStowSalmon() {
        val weapon = Weapon(0, "Test Weapon", "url")
        weapon.stow(splatnetDatabase, true)

        val storedWeapon = splatnetDatabase.weaponDao().selectAll()[0]

        Assert.assertEquals(weapon.toRoom(true), storedWeapon.weapon)
    }

    @Test
    fun test_weaponUpdateSubAndSpecial() {
        val special = Special(0, "Test Special", "url")
        val sub = Sub(0, "Test Sub", "url")
        var weapon = Weapon(0, "Test Weapon", "url")
        weapon.stow(splatnetDatabase, true)

        weapon = Weapon(0, "Test Weapon", "url", special, sub)
        weapon.stow(splatnetDatabase, false)

        val storedWeapon = splatnetDatabase.weaponDao().selectAll()[0]

        Assert.assertEquals(weapon.toRoom(true), storedWeapon.weapon)
        Assert.assertEquals(special, storedWeapon.special)
        Assert.assertEquals(sub, storedWeapon.sub)
    }

    @Test
    fun test_weaponUpdateSalmon() {
        val special = Special(0, "Test Special", "url")
        val sub = Sub(0, "Test Sub", "url")
        var weapon = Weapon(0, "Test Weapon", "url", special, sub)
        weapon.stow(splatnetDatabase, false)

        weapon = Weapon(0, "Test Weapon", "url")
        weapon.stow(splatnetDatabase, true)

        val storedWeapon = splatnetDatabase.weaponDao().selectAll()[0]

        weapon = Weapon(0, "Test Weapon", "url", special, sub)

        Assert.assertEquals(weapon.toRoom(true), storedWeapon.weapon)
        Assert.assertEquals(special, storedWeapon.special)
        Assert.assertEquals(sub, storedWeapon.sub)
    }

}
