package com.beryl.seabunne.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.ImageHandler
import com.squareup.picasso.Picasso

class GearImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    var gearName: String = ""
        set(value) {
            field = value.lowercase().replace(" ", "_")
            loadImage()
        }

    var gearUrl: String = ""
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
            val gearName = getString(R.styleable.WeaponImageView_weaponName)
            if (gearName != null) {
                this@GearImageView.gearName = gearName
            }

            val gearUrl = getString(R.styleable.WeaponImageView_weaponUrl)
            if (gearUrl != null) {
                this@GearImageView.gearUrl = gearUrl
            }
        }
    }

    private fun loadImage() {
        if (gearName.isNotBlank() && gearUrl.isNotBlank()) {
            if (ImageHandler.imageExists("gear", gearName, context.applicationContext)) {
                setImageBitmap(ImageHandler.loadImage("gear", gearName))
            } else {
                Picasso.with(context).load(gearUrl).into(this)
                ImageHandler.downloadImage("gear", gearName, gearUrl, context.applicationContext)
            }
        }
    }
}