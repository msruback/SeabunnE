package com.beryl.seabunne.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class AlertDialogFragment : DialogFragment() {

    val args: AlertDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            return AlertDialog(it, args.alertMessage)
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}