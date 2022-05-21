package com.yurdm.lab6

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NumberDialog(private val num: Int = 0) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setMessage("Your number is $num")
            .setTitle("Number")
            .setPositiveButton("OK", null)
            .create()
    }
}