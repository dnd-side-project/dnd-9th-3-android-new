package com.dnd_9th_3_android.gooding.trash.presentation.search_feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.data.model.search.SearchFeedData
import com.dnd_9th_3_android.gooding.databinding.ItemPopularKeywordBinding

class SearchFeedListAdapter(
    private val onClick: (SearchFeedData) -> Unit
) : ListAdapter<SearchFeedData, SearchFeedListAdapter.SearchFeedViewHolder>(SearchFeedDiffUtil) {

    class SearchFeedViewHolder(
        private val binding: ItemPopularKeywordBinding,
        private val onClick: (SearchFeedData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popularKeywordData: SearchFeedData) {
            binding.root.setOnClickListener {
                onClick(popularKeywordData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFeedViewHolder {
        val binding =
            ItemPopularKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFeedViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SearchFeedViewHolder, position: Int) {
        val popularKeyword = getItem(position)
        holder.bind(popularKeyword)
    }

    companion object SearchFeedDiffUtil : DiffUtil.ItemCallback<SearchFeedData>() {
        override fun areItemsTheSame(oldItem: SearchFeedData, newItem: SearchFeedData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchFeedData, newItem: SearchFeedData): Boolean {
            return oldItem == newItem
        }
    }
}