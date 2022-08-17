package com.beryl.seabunsource.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.beryl.seabunsource.R
import com.beryl.seabunsource.ui.ImageHandler
import com.squareup.picasso.Picasso

class StageImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    var stageUrl: String = ""
        set(value) {
            field = "https://app.splatoon2.nintendo.net$value"
            loadImage()
        }

    var stageName: String = ""
        set(value) {
            field = value.lowercase().replace(" ", "_")
            loadImage()
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.StageImageView,
            0, 0
        ).apply {
            try {
                val stageUrl = getString(R.styleable.StageImageView_stageUrl)
                if (stageUrl != null) {
                    this@StageImageView.stageUrl = stageUrl
                }

                val stageName = getString(R.styleable.StageImageView_stageName)
                if (stageName != null) {
                    this@StageImageView.stageName = stageName.lowercase().replace(" ", "_")
                }

                loadImage()
            } finally {
                recycle()
            }
        }
    }

    private fun loadImage() {
        if (stageName.isNotBlank() && stageUrl.isNotBlank()) {
            if (ImageHandler.imageExists("stage", stageName, context.applicationContext)) {
                setImageBitmap(ImageHandler.loadImage("stage", stageName))
            } else {
                Picasso.with(context).load(stageUrl).resize(1280, 720).into(this)
                ImageHandler.downloadImage("stage", stageName, stageUrl, context.applicationContext)
            }
        }
    }
}