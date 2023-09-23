package com.dnd_9th_3_android.gooding.trash.presentation.record.temp

import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dnd_9th_3_android.gooding.databinding.ItemRecordImageVideoFileBinding
import com.dnd_9th_3_android.gooding.databinding.ViewAddFileBinding
import com.dnd_9th_3_android.gooding.trash.presentation.util.fromDpToPx

class RecordImageVideoListAdapter(
    private val onClick: (RecordGalleryItem) -> Unit,
    private val onClickDelete: (RecordGalleryItem) -> Unit,
) : ListAdapter<RecordGalleryItem, RecordImageVideoListAdapter.RecordViewHolder>(
    object : DiffUtil.ItemCallback<RecordGalleryItem>() {
        override fun areItemsTheSame(
            oldItem: RecordGalleryItem,
            newItem: RecordGalleryItem
        ): Boolean {
            if (oldItem is RecordGalleryItem.File && newItem is RecordGalleryItem.File) {
                return oldItem.galleryFileUiData.id == newItem.galleryFileUiData.id
            }

            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecordGalleryItem,
            newItem: RecordGalleryItem
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordViewHolder {
        when (viewType) {
            TYPE_ADD_BUTTON -> {
                return RecordViewHolder.AddButton(
                    ViewAddFileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onClick
                )
            }
            TYPE_FILE -> {
                return RecordViewHolder.RecordImageVideo(
                    ItemRecordImageVideoFileBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onClick, onClickDelete
                )
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is RecordViewHolder.AddButton -> holder.bind(it as RecordGalleryItem.AddButton)
                is RecordViewHolder.RecordImageVideo -> holder.bind(it as RecordGalleryItem.File)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            RecordGalleryItem.AddButton -> TYPE_ADD_BUTTON
            is RecordGalleryItem.File -> TYPE_FILE
        }
    }

    companion object {
        private const val TYPE_ADD_BUTTON = 0
        private const val TYPE_FILE = 1
    }

    sealed class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class AddButton(
            private val binding: ViewAddFileBinding,
            private val onClick: (RecordGalleryItem) -> Unit,
        ) : RecordViewHolder(binding.root) {

            init {
                binding.root.background = GradientDrawable().apply {
                    cornerRadius = 2f.fromDpToPx().toFloat()
                    setStroke(
                        1.5f.fromDpToPx(),
                        ContextCompat.getColor(
                            itemView.context,
                            com.dnd_9th_3_android.gooding.core.data.R.color.blue_gray_4
                        )
                    )
                }
            }

            fun bind(item: RecordGalleryItem.AddButton) {
                binding.root.setOnClickListener {
                    onClick(item)
                }
            }
        }


        class RecordImageVideo(
            private val binding: ItemRecordImageVideoFileBinding,
            private val onClick: (RecordGalleryItem) -> Unit,
            private val onClickDelete: (RecordGalleryItem) -> Unit,
        ) : RecordViewHolder(binding.root) {

            fun bind(item: RecordGalleryItem.File) {
                binding.root.setOnClickListener {
                    onClick(item)
                }

                binding.ivDeleteFile.setOnClickListener {
                    onClickDelete(item)
                }

                val image = Uri.parse(item.galleryFileUiData.mediaData)

                Glide.with(itemView.context)
                    .load(image)
                    .into(binding.ivRecordImageVideoFile)

                binding.recordImageCover.isVisible = item.galleryFileUiData.selectedNumber == 1
            }
        }
    }
}