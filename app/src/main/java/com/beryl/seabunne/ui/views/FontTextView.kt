package com.beryl.seabunne.ui.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.beryl.seabunne.R

open class FontTextView(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FontView,
            0, 0
        ).apply {
            try {
                when (getInt(R.styleable.FontView_fontType, 0)) {
                    0 -> typeface = Typeface.createFromAsset(context.assets, "Paintball.otf")
                    1 -> typeface = Typeface.createFromAsset(context.assets, "Splatfont2.ttf")
                }
            } finally {
                recycle()
            }
        }
    }
}