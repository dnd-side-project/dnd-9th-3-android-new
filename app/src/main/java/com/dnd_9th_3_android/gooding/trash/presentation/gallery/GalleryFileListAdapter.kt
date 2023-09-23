package com.dnd_9th_3_android.gooding.trash.presentation.gallery

import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ItemGalleryImageBinding
import com.dnd_9th_3_android.gooding.presentation.util.fromDpToPx

class GalleryFileListAdapter(
    private val onClick: (GalleryFileUiData) -> Unit,
    private val isFullSelected: () -> Boolean,
) : PagingDataAdapter<GalleryFileUiData, GalleryFileListAdapter.GalleryFileItemViewHolder>(
    object : DiffUtil.ItemCallback<GalleryFileUiData>() {
        override fun areItemsTheSame(
            oldItem: GalleryFileUiData,
            newItem: GalleryFileUiData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GalleryFileUiData,
            newItem: GalleryFileUiData
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryFileItemViewHolder {
        val binding =
            ItemGalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GalleryFileItemViewHolder(binding, isFullSelected, onClick)
    }

    override fun onBindViewHolder(holder: GalleryFileItemViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    class GalleryFileItemViewHolder(
        private val binding: ItemGalleryImageBinding,
        private val isFullSelected: () -> Boolean,
        private val onClick: (GalleryFileUiData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.strokeView.background =
                GradientDrawable().apply {
                    setStroke(
                        1.5f.fromDpToPx(),
                        ContextCompat.getColor(itemView.context, R.color.primaryColor)
                    )
                }
        }

        fun bind(item: GalleryFileUiData) {
            binding.root.setOnClickListener {
                onClick(item)
            }

            binding.strokeView.isVisible = item.isSelected
//            val image = Uri.parse(item.mediaData)
            val image = if(absoluteAdapterPosition==0) {
                Uri.parse("https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/6be88bdd-99b9-4c84-831c-e169f39af82f1317a38b-f6bb-48df-9ffd-3fc719c2e8f2.jpeg")
            } else{
                Uri.parse("https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/11db12e9-c072-43b9-92bc-c8c988692573c6fc209c-3bc6-457f-883b-70dce4a063e8.jpeg")
            }
            Glide.with(itemView.context)
                .load(image)
                .into(binding.ivGalleryImage)

            binding.tvGalleryImageCount.isVisible = item.selectedNumber > 0
            binding.tvGalleryImageCount.text = item.selectedNumber.toString()
            binding.imageCover.isVisible = item.selectedNumber == 1

            binding.dimView.isVisible = isFullSelected() && !item.isSelected

            binding.galleryImage = item
            binding.executePendingBindings()
        }
    }
}