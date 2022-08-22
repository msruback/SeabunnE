package com.beryl.seabunne.data.splatnet2.salmonRun

import android.os.Parcelable
import com.beryl.seabunne.data.splatnet2.userInfo.Weapon
import kotlinx.parcelize.Parcelize

@Parcelize
class SalmonRunWeapon(val id: Int, val weapon: Weapon) : Parcelable
