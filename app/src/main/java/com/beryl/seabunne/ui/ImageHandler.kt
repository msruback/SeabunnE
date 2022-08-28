package com.beryl.seabunne.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.beryl.seabunne.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


object ImageHandler {

    fun imageExists(location: String, name: String, context: Context): Boolean {
        val dir = context.getFileStreamPath(location)
        if (!dir.exists()) {
            dir.mkdir()
        }
        return File(location, name).exists()
    }

    fun loadImage(location: String, name: String): Bitmap {
        val file = File(location, name)
        return BitmapFactory.decodeStream(FileInputStream(file))
    }

    fun downloadImage(location: String, name: String, url: String, context: Context) {
        val target: Target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                try {
                    val myDir = File(location, name)
                    val out = FileOutputStream(myDir)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                    out.flush()
                    out.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onBitmapFailed(errorDrawable: Drawable) {}
            override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
        }

        when (location) {
            "stage" -> Picasso.with(context).load(url).placeholder(R.drawable.stage_1)
                .resize(1280, 720).into(target)
            else -> Picasso.with(context).load(url).placeholder(R.drawable.stage_1).into(target)
        }
    }
}