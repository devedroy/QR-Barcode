package com.devedroy.qrbarcode

import android.graphics.Color
import java.io.Serializable


data class BarcodeView(
    val title: String?, val description: String?, val backgroundColor: Int
) : Serializable {
    data class Builder(
        private var title: String = "Barcode Scanner",
        private var description: String = "",
        private var backgroundColor: Int = Color.parseColor("#FFC0CB"),
    ) {
        fun setTitle(title: String) = apply { this.title = title }
        fun setDescription(description: String) = apply { this.description = description }
        fun setBackgroundColor(color: Int) = apply { this.backgroundColor = color }
        fun build() = BarcodeView(title, description, backgroundColor)
    }
}

