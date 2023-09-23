package com.dnd_9th_3_android.gooding.trash.presentation.search_feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.data.model.search.PopularKeywordData
import com.dnd_9th_3_android.gooding.databinding.ItemPopularKeywordBinding

class PopularKeywordListAdapter(
    private val onClick: (PopularKeywordData) -> Unit
) : ListAdapter<PopularKeywordData, PopularKeywordListAdapter.PopularKeywordViewHolder>(
    PopularKeywordDiffUtil
) {

    class PopularKeywordViewHolder(
        private val binding: ItemPopularKeywordBinding,
        private val onClick: (PopularKeywordData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popularKeywordData: PopularKeywordData) {
            binding.root.setOnClickListener {
                onClick(popularKeywordData)
            }

            binding.tvKeywordRank.text = popularKeywordData.rank.toString()
            binding.tvKeyword.text = popularKeywordData.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularKeywordViewHolder {
        val binding =
            ItemPopularKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularKeywordViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: PopularKeywordViewHolder, position: Int) {
        val popularKeyword = getItem(position)
        holder.bind(popularKeyword)
    }

    companion object PopularKeywordDiffUtil : DiffUtil.ItemCallback<PopularKeywordData>() {
        override fun areItemsTheSame(oldItem: PopularKeywordData, newItem: PopularKeywordData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularKeywordData, newItem: PopularKeywordData): Boolean {
            return oldItem == newItem
        }
    }
}