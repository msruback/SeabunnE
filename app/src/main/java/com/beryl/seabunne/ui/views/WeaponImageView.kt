package com.beryl.seabunne.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.ImageHandler
import com.squareup.picasso.Picasso

open class WeaponImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    var weaponName: String = ""
        set(value) {
            field = value.lowercase().replace(" ", "_") + ".jpeg"
            loadImage()
        }

    var weaponUrl: String = ""
        set(value) {
            field = "https://app.splatoon2.nintendo.net$value"
            loadImage()
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.WeaponImageView,
            0, 0
        ).apply {
            val weaponName = getString(R.styleable.WeaponImageView_weaponName)
            if (weaponName != null) {
                this@WeaponImageView.weaponName = weaponName
            }

            val weaponUrl = getString(R.styleable.WeaponImageView_weaponUrl)
            if (weaponUrl != null) {
                this@WeaponImageView.weaponUrl = weaponUrl
            }
        }
    }

    protected open fun loadImage() {
        if (weaponName.isNotBlank() && weaponUrl.isNotBlank()) {
            if (ImageHandler.imageExists("weapon", weaponName, context.applicationContext)) {
                setImageBitmap(ImageHandler.loadImage("weapon", weaponName))
            } else {
                Picasso.with(context).load(weaponUrl).into(this)
                ImageHandler.downloadImage(
                    "weapon",
                    weaponName,
                    weaponUrl,
                    context.applicationContext
                )
            }
        }
    }
}