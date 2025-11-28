package com.yogi.portfolio.portfolio.Utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.yogi.portfolio.databinding.DialogProgressBinding

class CommonProgressDialog(private val context: Context) {

    private var dialog: Dialog? = null
    private lateinit var binding: DialogProgressBinding


    fun show(message: String = "Loading...") {
        if (dialog?.isShowing == true) return

        dialog = Dialog(context)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogProgressBinding.inflate(LayoutInflater.from(context))
        dialog?.setContentView(binding.root)
        dialog?.setCancelable(false)

        binding.tvMessage.text = message

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()
    }

    fun dismiss() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
            dialog = null
        }
    }
}