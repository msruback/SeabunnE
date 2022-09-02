package com.beryl.seabunne.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CookieDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            return CookieDialog(it)
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}