package com.dnd_9th_3_android.gooding.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ViewRecordInputBinding
import com.dnd_9th_3_android.gooding.presentation.util.fromDpToPx


@SuppressLint("SetTextI18n", "ClickableViewAccessibility")
class RecordInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewRecordInputBinding =
        ViewRecordInputBinding.inflate(LayoutInflater.from(context), this)

    init {
        val a =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.RecordInputView
            )
        val isSingleLine = a.getBoolean(
            R.styleable.RecordInputView_isSingleLine,
            false
        )
        a.recycle()
        binding.inputContainer.setSingleLine(isSingleLine)

        binding.root.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        binding.inputContainer.setTextChangedListener { text ->
            disableError()
        }
    }

    fun setText(text: String) {
        binding.inputContainer.setText(text)
    }

    fun setHint(textHint: String) {
        binding.inputContainer.setHint(textHint)
    }

    fun setError(message: String) {
        binding.warning.isVisible = true
        binding.tvWarning.text = message

        binding.inputContainer.setStroke(1.5f.fromDpToPx(), R.color.warning)
    }

    fun disableError() {
        if (binding.warning.isGone) return

        binding.warning.isGone = true
        binding.tvWarning.text = ""

        binding.inputContainer.resetStroke()
    }

}