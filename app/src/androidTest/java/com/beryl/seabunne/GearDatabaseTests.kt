package com.beryl.seabunne

import com.beryl.seabunne.data.database.entities.userInfo.BrandEntity
import com.beryl.seabunne.data.database.entities.userInfo.Clothes
import com.beryl.seabunne.data.database.entities.userInfo.Headgear
import com.beryl.seabunne.data.database.entities.userInfo.Shoes
import com.beryl.seabunne.data.splatnet2.userInfo.Brand
import com.beryl.seabunne.data.splatnet2.userInfo.Gear
import com.beryl.seabunne.data.splatnet2.userInfo.entities.Skill
import org.junit.Assert
import org.junit.Test

class GearDatabaseTests : DatabaseTest() {

    @Test
    fun test_skillInsert() {
        val skill = Skill(0, "Skill", "url")

        splatnetDatabase.skillDao().insertAll(skill)

        val storedSkill = splatnetDatabase.skillDao().selectAll()[0]

        Assert.assertEquals(skill, storedSkill)
    }

    @Test
    fun test_brandInsert() {
        val skill = Skill(0, "Skill", "url")
        val brand = BrandEntity(0, "Brand", "url", 0)

        splatnetDatabase.skillDao().insertAll(skill)
        splatnetDatabase.brandDao().insertAll(brand)

        val storedBrand = splatnetDatabase.brandDao().selectAll()[0]

        Assert.assertEquals(brand, storedBrand.brand)
        Assert.assertEquals(skill, storedBrand.skill)
    }

    @Test
    fun test_brandStow() {
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)

        brand.stow(splatnetDatabase)

        val storedBrand = splatnetDatabase.brandDao().selectAll()[0]

        Assert.assertEquals(brand.toRoom(), storedBrand.brand)
        Assert.assertEquals(skill, storedBrand.skill)
    }

    @Test
    fun test_headgearInsert() {
        val skill = Skill(0, "Skill", "url")
        val brand = BrandEntity(0, "Brand", "url", 0)
        val headgear = Headgear(0, "head", "hat", 0, "url", 0)

        splatnetDatabase.skillDao().insertAll(skill)
        splatnetDatabase.brandDao().insertAll(brand)
        splatnetDatabase.headgearDao().insertAll(headgear)

        val storedGear = splatnetDatabase.headgearDao().selectAll()[0]

        Assert.assertEquals(headgear, storedGear.headgear)
        Assert.assertEquals(brand, storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }

    @Test
    fun test_headgearStow() {
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "head", "hat", 0, "url", brand)

        gear.stow(splatnetDatabase)

        val storedGear = splatnetDatabase.headgearDao().selectAll()[0]

        Assert.assertEquals(gear.toRoomHeadgear(), storedGear.headgear)
        Assert.assertEquals(brand.toRoom(), storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }

    @Test
    fun test_clothesInsert() {
        val skill = Skill(0, "Skill", "url")
        val brand = BrandEntity(0, "Brand", "url", 0)
        val clothes = Clothes(0, "head", "hat", 0, "url", 0)

        splatnetDatabase.skillDao().insertAll(skill)
        splatnetDatabase.brandDao().insertAll(brand)
        splatnetDatabase.clothesDao().insertAll(clothes)

        val storedGear = splatnetDatabase.clothesDao().selectAll()[0]

        Assert.assertEquals(clothes, storedGear.clothes)
        Assert.assertEquals(brand, storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }

    @Test
    fun test_clothesStow() {
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "clothes", "hat", 0, "url", brand)

        gear.stow(splatnetDatabase)

        val storedGear = splatnetDatabase.clothesDao().selectAll()[0]

        Assert.assertEquals(gear.toRoomClothes(), storedGear.clothes)
        Assert.assertEquals(brand.toRoom(), storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }

    @Test
    fun test_shoesInsert() {
        val skill = Skill(0, "Skill", "url")
        val brand = BrandEntity(0, "Brand", "url", 0)
        val shoes = Shoes(0, "shoes", "hat", 0, "url", 0)

        splatnetDatabase.skillDao().insertAll(skill)
        splatnetDatabase.brandDao().insertAll(brand)
        splatnetDatabase.shoesDao().insertAll(shoes)

        val storedGear = splatnetDatabase.shoesDao().selectAll()[0]

        Assert.assertEquals(shoes, storedGear.shoes)
        Assert.assertEquals(brand, storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }

    @Test
    fun test_shoesStow() {
        val skill = Skill(0, "Skill", "url")
        val brand = Brand(0, "Brand", "url", skill)
        val gear = Gear(0, "shoes", "hat", 0, "url", brand)

        gear.stow(splatnetDatabase)

        val storedGear = splatnetDatabase.shoesDao().selectAll()[0]

        Assert.assertEquals(gear.toRoomShoes(), storedGear.shoes)
        Assert.assertEquals(brand.toRoom(), storedGear.brand.brand)
        Assert.assertEquals(skill, storedGear.brand.skill)
    }
}
