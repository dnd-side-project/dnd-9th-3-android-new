package com.dnd_9th_3_android.gooding.presentation.gallery.thumbnailExt

import android.graphics.drawable.Drawable

interface PlayableView {
    /**
     * Set thumbnail of Playable
     *
     * This is called usually when RecyclerView#onBindViewHolder is called
     */
    fun setThumbnail(url: String?, placeholder: Drawable? = null)

    fun showThumbnail()

    fun hideThumbnail()

    fun showLoading()

    fun hideLoading()
}