package com.dnd_9th_3_android.gooding.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.dnd_9th_3_android.gooding.R

class ClearAndSearchEditText: AppCompatEditText, TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
    private lateinit var clearDrawable: Drawable
    private lateinit var onFocusChangeListener: OnFocusChangeListener
    private lateinit var onTouchListener: OnTouchListener
    private var count: Int = 0

    constructor(context: Context?): super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?): super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context!!, attrs, defStyleAttr) {
        init()
    }

    override fun setOnFocusChangeListener(l: OnFocusChangeListener) {
        onFocusChangeListener = l
        count = 1
    }

    override fun setOnTouchListener(l: OnTouchListener) {
        onTouchListener = l
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (hasFocus() && text != null) {
            setClearIconVisible(text!!.isNotEmpty())
        } else {
            setClearIconVisible(false)
        }

        if (count == 1) {
            onFocusChangeListener.onFocusChange(view, hasFocus)
        }
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        try {
            val x = event.x.toInt()

            if (clearDrawable.isVisible && x > width - paddingEnd - clearDrawable.intrinsicWidth) {
                if (event.action == MotionEvent.ACTION_UP) {
                    error = null
                    text = null
                }
                return true
            }

            return onTouchListener.onTouch(view, event)
        } catch (i: Exception) {
            return false
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (isFocused) setClearIconVisible(text.isNotEmpty())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }

    private fun setClearIconVisible(visible: Boolean) {
        clearDrawable.setVisible(visible, false)
        setCompoundDrawables(
            null,
            null,
            if (visible) clearDrawable else null,
            null
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        clearDrawable = DrawableCompat.wrap(
            ResourcesCompat.getDrawable(resources, R.drawable.icon_clear_text, null) as Drawable
        )

        DrawableCompat.setTintList(clearDrawable, hintTextColors)
        clearDrawable.setBounds(0, 0, clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)

        clearDrawable.colorFilter = PorterDuffColorFilter(context.getColor(R.color.black), PorterDuff.Mode.SRC_IN)

        setClearIconVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }
}