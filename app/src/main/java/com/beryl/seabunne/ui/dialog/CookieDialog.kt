package com.beryl.seabunne.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.beryl.seabunne.R
import com.beryl.seabunne.ui.views.FontEditText
import com.beryl.seabunne.ui.views.FontTextView

class CookieDialog(activity: Activity) : Dialog(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_cookie)

        val updateButton = findViewById<FontTextView>(R.id.update_button)
        val cancelButton = findViewById<FontTextView>(R.id.cancel_button)
        val cookieEditText = findViewById<FontEditText>(R.id.cookie)

        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )

        cookieEditText.setText(
            sharedPreferences.getString("cookie", "")?.replace("iksm_session=", "")
        )

        updateButton.setOnClickListener {
            sharedPreferences.edit()
                .putString("cookie", "iksm_session=${cookieEditText.text}")
                .apply()
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}
