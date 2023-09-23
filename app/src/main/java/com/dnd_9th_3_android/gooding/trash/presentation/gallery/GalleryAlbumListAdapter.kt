package com.dnd_9th_3_android.gooding.trash.presentation.gallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.databinding.ItemGalleryAlbumBinding


class GalleryAlbumListAdapter(
    private val onClick: (GalleryAlbumUiData) -> Unit
) : ListAdapter<GalleryAlbumUiData, GalleryAlbumListAdapter.AlbumViewHolder>(object :
    DiffUtil.ItemCallback<GalleryAlbumUiData>() {
    override fun areItemsTheSame(oldItem: GalleryAlbumUiData, newItem: GalleryAlbumUiData): Boolean {
        return oldItem.folderName == newItem.folderName
    }

    override fun areContentsTheSame(oldItem: GalleryAlbumUiData, newItem: GalleryAlbumUiData): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(parent, onClick)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AlbumViewHolder(
        parent: ViewGroup,
        private val onClick: (GalleryAlbumUiData) -> Unit
    ) :
        RecyclerView.ViewHolder(
            ItemGalleryAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        ) {
        private val binding = ItemGalleryAlbumBinding.bind(itemView)

        fun bind(item: GalleryAlbumUiData) {
            binding.root.setOnClickListener {
                onClick(item)
            }

//            binding.ivAlbumImage.setImageURI(item.thumbnail)
            binding.ivAlbumImage.setImageURI(Uri.parse("https://dnd9th-3.s3.ap-northeast-2.amazonaws.com/images/6be88bdd-99b9-4c84-831c-e169f39af82f1317a38b-f6bb-48df-9ffd-3fc719c2e8f2.jpeg"))
            binding.tvFolderName.text = item.folderName
            binding.tvFolderFileCount.text = item.folderFileCount.toString()
        }
    }
}

