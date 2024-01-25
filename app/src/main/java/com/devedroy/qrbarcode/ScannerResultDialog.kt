package com.devedroy.qrbarcode

import android.content.DialogInterface
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.devedroy.qrbarcode.databinding.FragmentScannerResultDialogListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val ARG_SCANNING_RESULT = "scanning_result"

class ScannerResultDialog(private val listener: DialogDismissListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentScannerResultDialogListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScannerResultDialogListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannedResult = arguments?.getString(ARG_SCANNING_RESULT)
        binding.etResult.setText(scannedResult)

    }

    companion object {
        @JvmStatic
        fun newInstance(
            scanningResult: String, listener: DialogDismissListener
        ): ScannerResultDialog = ScannerResultDialog(listener).apply {
            arguments = Bundle().apply {
                putString(ARG_SCANNING_RESULT, scanningResult)
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onDismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        listener.onDismiss()
    }

    interface DialogDismissListener {
        fun onDismiss()
    }
}