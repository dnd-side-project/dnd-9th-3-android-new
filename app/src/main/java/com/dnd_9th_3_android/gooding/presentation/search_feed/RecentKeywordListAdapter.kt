package com.dnd_9th_3_android.gooding.presentation.search_feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.data.model.search.RecentKeywordData
import com.dnd_9th_3_android.gooding.databinding.ItemRecentKeywordBinding

class RecentKeywordListAdapter(
    private val onClick: (RecentKeywordData) -> Unit
) : ListAdapter<RecentKeywordData, RecentKeywordListAdapter.RecentFeedViewHolder>(
    RecentKeywordDiffUtil
) {

    class RecentFeedViewHolder(
        private val binding: ItemRecentKeywordBinding,
        private val onClick: (RecentKeywordData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recentKeywordData: RecentKeywordData) {
            binding.root.setOnClickListener {
                onClick(recentKeywordData)
            }

            binding.tvKeyword.text = recentKeywordData.content

            binding.ivDelete.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentFeedViewHolder {
        val binding =
            ItemRecentKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentFeedViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: RecentFeedViewHolder, position: Int) {
        val recentKeyword = getItem(position)
        holder.bind(recentKeyword)
    }

    companion object RecentKeywordDiffUtil : DiffUtil.ItemCallback<RecentKeywordData>() {
        override fun areItemsTheSame(oldItem: RecentKeywordData, newItem: RecentKeywordData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecentKeywordData, newItem: RecentKeywordData): Boolean {
            return oldItem == newItem
        }
    }
}