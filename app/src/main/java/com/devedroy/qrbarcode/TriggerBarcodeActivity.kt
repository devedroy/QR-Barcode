package com.devedroy.qrbarcode

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.devedroy.qrbarcode.utils.Utils

class TriggerBarcodeActivity : AppCompatActivity() {
    private val CAMERA_PERMISSION_CODE = 69
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Utils.hasRearCamera(applicationContext)) {
            Toast.makeText(this@TriggerBarcodeActivity, "You have rear camera", Toast.LENGTH_SHORT)
                .show()
            checkPermsAndTriggerScan()
        } else {
            Toast.makeText(
                this@TriggerBarcodeActivity, "You don't have rear camera", Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }

    private fun checkPermsAndTriggerScan() {
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openScanner()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE
            )
        }
    }

    private fun openScanner() {
        val intent = Intent(this, BarCodeScannerActivity::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openScanner()
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    this, android.Manifest.permission.CAMERA
                )
            ) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivityForResult(intent, CAMERA_PERMISSION_CODE)
            }
        }
    }

}