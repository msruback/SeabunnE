package com.beryl.seabunne.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.beryl.seabunne.R

class SalmonWeaponImageView(context: Context, attrs: AttributeSet? = null) :
    WeaponImageView(context, attrs) {

    var weaponType: String = "normal"

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SalmonWeaponImageView,
            0, 0
        ).apply {
            val type = getString(R.styleable.FontTextView_fontType)
            if (type != null) {
                weaponType = type
            }
        }
    }

    override fun loadImage() {
        when (weaponType) {
            "mystery" -> setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.weapon_mystery
                )
            )
            "grizz" -> setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.weapon_mystery_grizzco
                )
            )
            else -> super.loadImage()
        }
    }
}