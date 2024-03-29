package com.devedroy.qrbarcode

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class ViewFinderOverlay(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val boxPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.white)
        style = Paint.Style.STROKE
        strokeWidth =
            context.resources.getDimensionPixelOffset(R.dimen.barcode_reticle_stroke_width)
                .toFloat()
    }

    private val scrimPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.barcode_reticle_background)
    }

    private val eraserPaint: Paint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private val boxCornerRadius: Float =
        context.resources.getDimensionPixelOffset(R.dimen.barcode_reticle_stroke_width).toFloat()

    private var boxRect: RectF? = null

    fun setViewFinder() {
        val overLayWidth = width.toFloat()
        val overLayHeight = height.toFloat()
        val boxWidth = overLayWidth * 80 / 100
        val boxHeight = overLayHeight * 36 / 100

        val cx = overLayWidth / 2
        val cy = overLayHeight / 2

        boxRect =
            RectF(cx - boxWidth / 2, cy - boxHeight / 2, cx + boxWidth / 2, cy + boxHeight / 2)

        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        boxRect?.let {
            canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), scrimPaint)
            eraserPaint.style = Paint.Style.FILL
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, eraserPaint)
            eraserPaint.style = Paint.Style.STROKE
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, eraserPaint)
            canvas.drawRoundRect(it, boxCornerRadius, boxCornerRadius, boxPaint)
        }
    }
}