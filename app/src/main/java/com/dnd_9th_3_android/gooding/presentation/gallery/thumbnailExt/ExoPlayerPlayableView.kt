package com.dnd_9th_3_android.gooding.presentation.gallery.thumbnailExt

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import androidx.core.view.isInvisible
import com.google.android.exoplayer2.ui.PlayerView
import loadUrlAsync

class ExoPlayerPlayableView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : PlayerView(context, attrs),
    PlayableView {

    private val thumbnailView = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    init {
        addView(thumbnailView, LayoutParams(MATCH_PARENT, MATCH_PARENT))

        setShowBuffering(SHOW_BUFFERING_NEVER)
    }

    private fun toPixel(dp: Int): Float = resources.displayMetrics.density * dp

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun setThumbnail(url: String?, placeholder: Drawable?) {
        thumbnailView.loadUrlAsync(url, placeholder)
    }

    override fun showThumbnail() {
        thumbnailView.isInvisible = false
    }

    override fun hideThumbnail() {
        thumbnailView.isInvisible = true
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}