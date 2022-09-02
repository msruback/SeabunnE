package com.beryl.seabunne.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.views.FontTextView

class AlertDialog(activity: Activity, private val message: String) : Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_alert)

        findViewById<FontTextView>(R.id.message).text = message
        findViewById<ConstraintLayout>(R.id.dialog_layout).clipToOutline = true
    }
}
