package com.beryl.seabunne.ui.views

import android.content.Context
import android.util.AttributeSet
import com.beryl.seabunne.R
import java.text.SimpleDateFormat
import java.util.*


class TimeRangeTextView(context: Context, attrs: AttributeSet? = null) :
    FontTextView(context, attrs) {

    var start: Long = -1
        set(value) {
            field = value
            formatText()
        }

    var end: Long = -1
        set(value) {
            field = value
            formatText()
        }

    var includeDate: Boolean = false

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TimeRangeTextView,
            0, 0
        ).apply {
            try {
                start = getInt(R.styleable.TimeRangeTextView_start, -1).toLong()
                end = getInt(R.styleable.TimeRangeTextView_end, -1).toLong()
                includeDate = getBoolean(R.styleable.TimeRangeTextView_includeDate, false)
                formatText()
            } finally {
                recycle()
            }
        }
    }

    private fun formatText() {
        val sdf = if (includeDate) {
            SimpleDateFormat("EEE h:mm a", Locale.getDefault())
        } else {
            SimpleDateFormat("h:mm a", Locale.getDefault())
        }
        val startText = sdf.format(Date(start * 1000))
        val endText = sdf.format(Date(end * 1000))

        "$startText - $endText".also { text = it }
        "$startText to $endText".also { contentDescription = it }
    }
}