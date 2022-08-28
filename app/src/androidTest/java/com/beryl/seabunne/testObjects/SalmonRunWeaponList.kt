package com.beryl.seabunne.testObjects

import com.beryl.seabunne.data.database.entities.userInfo.WeaponEntity
import com.beryl.seabunne.data.database.pojos.salmonRun.SalmonRunWeaponPojo
import com.beryl.seabunne.data.splatnet2.salmonRun.SalmonRunWeapon
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon

class SalmonRunWeaponList(val salmonRunWeapons: List<SalmonRunWeapon>, salmonRunStartTime: Long) {

    val salmonRunWeaponPojos: List<SalmonRunWeaponPojo>
    val weapons: List<Weapon>
    val weaponEntities: List<WeaponEntity>

    init {
        val salmonRunWeaponPojos = mutableListOf<SalmonRunWeaponPojo>()
        val weapons = mutableListOf<Weapon>()
        val weaponEntities = mutableListOf<WeaponEntity>()

        salmonRunWeapons.forEach {
            if (it.weapon != null) {
                weapons.add(it.weapon!!)
                weaponEntities.add(it.weapon!!.toRoom(true))
            }
            salmonRunWeaponPojos.add(
                SalmonRunWeaponPojo(
                    it.toRoom(
                        salmonRunStartTime,
                        salmonRunWeapons.indexOf(it)
                    ), it.weapon?.toRoom(true)
                )
            )
        }

        this.salmonRunWeaponPojos = salmonRunWeaponPojos.toList()
        this.weapons = weapons.toList()
        this.weaponEntities = weaponEntities.toList()
    }
}