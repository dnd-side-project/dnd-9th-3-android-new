package com.dnd_9th_3_android.gooding.presentation.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ViewCenterToastBinding

class CenterToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewCenterToastBinding =
        ViewCenterToastBinding.inflate(LayoutInflater.from(context), this)

    init {
        background = ContextCompat.getDrawable(context, R.color.tertiaryColor)
    }

    fun setText(text: String) {
        binding.text.text = text
    }
}