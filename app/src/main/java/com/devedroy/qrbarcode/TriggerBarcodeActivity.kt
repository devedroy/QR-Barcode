package com.devedroy.qrbarcode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devedroy.qrbarcode.utils.Utils

class TriggerBarcodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Utils.hasRearCamera(applicationContext)) {
            Toast.makeText(this@TriggerBarcodeActivity, "You have rear camera", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this@TriggerBarcodeActivity, "You don't have rear camera", Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }

}