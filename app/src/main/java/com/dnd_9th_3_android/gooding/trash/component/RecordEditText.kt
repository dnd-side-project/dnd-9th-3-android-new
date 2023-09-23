package com.dnd_9th_3_android.gooding.trash.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.widget.doOnTextChanged
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ViewRecordEditBinding
import com.dnd_9th_3_android.gooding.trash.presentation.util.fromDpToPx


@SuppressLint("ClickableViewAccessibility", "SetTextI18n")
class RecordEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewRecordEditBinding =
        ViewRecordEditBinding.inflate(LayoutInflater.from(context), this, true)

    private val defaultInputBackground: GradientDrawable
        get() = GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, R.color.tertiaryColor))
            cornerRadius = 6f.fromDpToPx().toFloat()
        }

    private var onTextChangedListener: ((String) -> Unit)? = null

    private var isSingleLine = false

    private var maxTextCount = 100

    init {
        val a =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.RecordInputView
            )
        isSingleLine = a.getBoolean(
            R.styleable.RecordInputView_isSingleLine,
            false
        )
        a.recycle()

        binding.inputContainer.background = defaultInputBackground

        binding.inputView.setOnFocusChangeListener { _, hasFocus ->
            binding.inputContainer.background = if (hasFocus) {
                defaultInputBackground.apply {
                    setStroke(
                        1.5f.fromDpToPx(),
                        ContextCompat.getColor(context, R.color.primaryColor)
                    )
                }
            } else {
                defaultInputBackground
            }
        }

        binding.inputView.doOnTextChanged { text, _, _, _ ->
            binding.textCount.text = "${text?.length ?: 0}/${maxTextCount}"

            onTextChangedListener?.invoke(text.toString())
        }

        binding.inputView.setOnTouchListener { view, event ->
            if (binding.inputView.hasFocus()) {
                view.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        view.parent.requestDisallowInterceptTouchEvent(false)
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        setMaxTextCount(maxTextCount)
    }


    fun setStroke(width: Int, @ColorRes colorResId: Int) {
        binding.inputContainer.background = defaultInputBackground.apply {
            setStroke(width, ContextCompat.getColor(context, colorResId))
        }
    }

    fun resetStroke() {
        binding.inputContainer.background = defaultInputBackground
    }

    fun setText(text: String) {
        binding.inputView.setText(text)
    }

    fun setHint(textHint: String) {
        binding.inputView.hint = textHint
        binding.inputView.setHintTextColor(
            ContextCompat.getColor(
                context,
                R.color.quaternaryColor
            )
        )
    }

    fun setMaxTextCount(maxTextCount: Int) {
        this.maxTextCount = maxTextCount
        binding.textCount.text = "0/$maxTextCount"
        setEditTextMaxLength(maxTextCount)
    }

    private fun setEditTextMaxLength(length: Int) {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(length)
        binding.inputView.filters = filterArray
    }


    fun setTextChangedListener(onChanged: (String) -> Unit) {
        onTextChangedListener = onChanged
    }

    fun setSingleLine(isSingleLine: Boolean) {
        this.isSingleLine = isSingleLine

        if (isSingleLine) {
            binding.inputContainer.minimumHeight = 48f.fromDpToPx()

            binding.inputView.updateLayoutParams<LayoutParams> {
                bottomToTop = ConstraintSet.UNSET
                endToEnd = ConstraintSet.UNSET
                endToStart = binding.textCount.id
                bottomToBottom = binding.root.id

                verticalBias = 0.5f

                updateMargins(top = 0f.fromDpToPx(), bottom = 0f.fromDpToPx())
            }
            binding.inputView.maxLines = 1

            binding.textCount.updateLayoutParams<LayoutParams> {
                topToTop = binding.root.id

                updateMargins(top = 14f.fromDpToPx(), bottom = 13f.fromDpToPx())
            }

            setMaxTextCount(20)
        } else {
            binding.inputContainer.minimumHeight = 136f.fromDpToPx()

            binding.inputView.updateLayoutParams<LayoutParams> {
                bottomToTop = binding.textCount.id
                endToEnd = binding.root.id
                endToStart = ConstraintSet.UNSET
                bottomToBottom = ConstraintSet.UNSET

                verticalBias = 0f

                updateMargins(top = 14f.fromDpToPx(), bottom = 28f.fromDpToPx())
            }
            binding.inputView.maxLines = Int.MAX_VALUE

            binding.textCount.updateLayoutParams<LayoutParams> {
                topToTop = ConstraintSet.UNSET

                updateMargins(bottom = 13f.fromDpToPx())
            }

            setMaxTextCount(100)
        }
    }
}