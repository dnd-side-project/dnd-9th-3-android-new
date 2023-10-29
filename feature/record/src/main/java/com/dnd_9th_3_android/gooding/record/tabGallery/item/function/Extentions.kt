package com.dnd_9th_3_android.gooding.record.tabGallery.item.function

import androidx.navigation.NavBackStackEntry
import com.dnd_9th_3_android.gooding.model.record.GalleryImage

fun getSelectedImageFromBackStack(navBackStackEntry: NavBackStackEntry?) =
    navBackStackEntry?.savedStateHandle?.getLiveData<List<GalleryImage>>(BITMAP_IMAGES)?.value