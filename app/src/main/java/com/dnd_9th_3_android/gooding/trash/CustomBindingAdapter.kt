package com.dnd_9th_3_android.gooding.trash

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("duration")
fun setDuration(view: TextView, duration: Long) {
    if (duration == 0L) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
//        view.text = Utils.getPrettyDuration(duration)
    }
}

fun ImageView.loadUrlAsync(url: String?, placeholder: Drawable? = null) {
    if (url == null) {
        Glide.with(this).load(placeholder).into(this)
    } else {
        Glide.with(this).load(url)
            .apply {
                if (placeholder != null) placeholder(placeholder)
            }
            .into(this)
    }
}