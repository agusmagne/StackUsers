package com.example.stackusers.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.stackusers.R

class ErrorDialogFragment : DialogFragment() {

    companion object {
        const val ERROR_STRING_RES = "ERROR_STRING_RES"
        fun newInstance(error: Int): ErrorDialogFragment {
            val df = ErrorDialogFragment()
            val bundle = Bundle()
            bundle.putInt(ERROR_STRING_RES, error)
            df.arguments = bundle
            return df
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val errorStringRes = arguments?.getInt(ERROR_STRING_RES) ?: R.string.unknown_error
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.error)
            .setMessage(errorStringRes)
            .setPositiveButton(R.string.dismiss) { _, _ -> dismiss() }.show()
    }
}