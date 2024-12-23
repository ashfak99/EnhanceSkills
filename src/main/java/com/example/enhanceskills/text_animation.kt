package com.example.enhanceskills

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView

class text_animation(
    private val textView: TextView,
    private val texts: List<String>,
    private val typingSpeed: Long = 100,
    private val deletingSpeed: Long = 50,
    private val delayBetweenTexts: Long = 1000
) {

    private var currentTextIndex = 0
    private var isDeleting = false
    private val handler = Handler(Looper.getMainLooper())

    fun start() {
        handler.post(run)
    }

    private val run = object : Runnable {
        override fun run() {
            val currentText = texts[currentTextIndex]
            val currentTextLength = textView.text.length

            if (!isDeleting) {
                if (currentTextLength < currentText.length) {
                    textView.text = currentText.substring(0, currentTextLength + 1)
                    handler.postDelayed(this, typingSpeed)
                } else {
                    applyStyleAnimation()
                    isDeleting = true
                    handler.postDelayed(this, delayBetweenTexts)
                }
            } else {
                if (currentTextLength > 0) {
                    textView.text = currentText.substring(0, currentTextLength - 1)
                    handler.postDelayed(this, deletingSpeed)
                } else {
                    isDeleting = false
                    currentTextIndex = (currentTextIndex + 1) % texts.size
                    handler.postDelayed(this, delayBetweenTexts)
                }
            }
        }
    }

    private fun applyStyleAnimation() {
        val scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 1f, 1.2f, 1f)
        val alpha = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0.7f, 1f)
        val colorFade = ObjectAnimator.ofArgb(
            textView,
            "textColor",
            textView.currentTextColor,
            textView.currentTextColor.shade(0.7f)
        )

        val animatorSet = AnimatorSet().apply {
            playTogether(scaleX, scaleY, alpha, colorFade)
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
        }

        animatorSet.start()
    }

    // Extension function to shade color
    private fun Int.shade(factor: Float): Int {
        val a = this shr 24 and 0xff
        val r = ((this shr 16 and 0xff) * factor).toInt()
        val g = ((this shr 8 and 0xff) * factor).toInt()
        val b = ((this and 0xff) * factor).toInt()
        return a shl 24 or (r shl 16) or (g shl 8) or b
    }
}
