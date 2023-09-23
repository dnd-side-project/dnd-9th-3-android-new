package com.dnd_9th_3_android.gooding.trash.component

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ViewCenterToastBinding
import com.dnd_9th_3_android.gooding.trash.presentation.util.fromDpToPx


class CenterToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewCenterToastBinding =
        ViewCenterToastBinding.inflate(LayoutInflater.from(context), this)

    init {
        background = GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, R.color.tertiaryColor))
            cornerRadius = 15f.fromDpToPx().toFloat()
        }
    }

    fun setText(text: String) {
        binding.text.text = text
    }
}