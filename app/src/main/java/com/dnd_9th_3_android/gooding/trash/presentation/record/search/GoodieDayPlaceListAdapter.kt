package com.dnd_9th_3_android.gooding.trash.presentation.record.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.trash.data.model.map.KakaoMapDocuments
import com.dnd_9th_3_android.gooding.databinding.ItemKakaoMapPlaceBinding

class GoodieDayPlaceListAdapter(
    private val onClick: (KakaoMapDocuments) -> Unit,
) : ListAdapter<KakaoMapDocuments, GoodieDayPlaceListAdapter.GoodieDayPlaceViewHolder>(DiffCallback()) {

    class GoodieDayPlaceViewHolder(
        val binding: ItemKakaoMapPlaceBinding,
        private val onClick: (KakaoMapDocuments) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mapData: KakaoMapDocuments) {
            binding.tvPlaceName.text = mapData.placeName
//            binding.tvPlaceDistance.text = mapData.distance
            binding.tvPlaceAddress.text = mapData.addressName

            binding.root.setOnClickListener {
                onClick(mapData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodieDayPlaceViewHolder {
        val binding =
            ItemKakaoMapPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GoodieDayPlaceViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: GoodieDayPlaceViewHolder, position: Int) {
        val mapItem = currentList[position]

        holder.bind(mapItem)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private class DiffCallback : DiffUtil.ItemCallback<KakaoMapDocuments>() {
        override fun areItemsTheSame(oldItem: KakaoMapDocuments, newItem: KakaoMapDocuments): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: KakaoMapDocuments, newItem: KakaoMapDocuments): Boolean {
            return oldItem == newItem
        }
    }
}