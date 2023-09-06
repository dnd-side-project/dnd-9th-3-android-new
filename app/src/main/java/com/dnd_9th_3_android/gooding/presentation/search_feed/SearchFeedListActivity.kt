package com.dnd_9th_3_android.gooding.presentation.search_feed

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.dnd_9th_3_android.gooding.data.model.search.PopularKeywordData
import com.dnd_9th_3_android.gooding.data.model.search.RecentKeywordData
import com.dnd_9th_3_android.gooding.data.model.search.SearchFeedData
import com.dnd_9th_3_android.gooding.databinding.ActivitySearchFeedListBinding
import com.dnd_9th_3_android.gooding.databinding.ItemPopularKeywordBinding
import com.dnd_9th_3_android.gooding.databinding.ItemRecentKeywordBinding
import com.dnd_9th_3_android.gooding.presentation.util.fromDpToPx
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFeedListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchFeedListBinding
    private lateinit var recentKeywordListAdapter: RecentKeywordListAdapter
    private lateinit var popularKeywordListAdapter: PopularKeywordListAdapter
    private lateinit var searchFeedListAdapter: SearchFeedListAdapter

    private val viewModel by viewModels<SearchFeedListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchFeedListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initViewModel()
        initListeners()
    }

    private fun initView() {
        initRecentKeywordListRecyclerView(binding.rvRecentKeywordList)
        initPopularKeywordListRecyclerView(binding.rvPopularKeywordList)
        initSearchFeedListRecyclerView(binding.rvSearchedFeedList)

        TedKeyboardObserver(this)
            .listen { isShow ->
                val isItemEmpty = searchFeedListAdapter.itemCount == 0
                binding.rvSearchedFeedList.isInvisible = isShow || isItemEmpty
//                binding.tvNothingFound.isVisible = !isShow && isItemEmpty

                if (!isShow) {
                    binding.textEditSearchFeed.clearFocus()
                }
            }
    }

    private fun initRecentKeywordListRecyclerView(recyclerView: RecyclerView) {
        recentKeywordListAdapter = RecentKeywordListAdapter(
            onClick = ::onClickRecentKeywordItem
        )

        recyclerView.run {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = recentKeywordListAdapter

            val spaceDecoration = HorizontalSpaceItemDecoration(8f.fromDpToPx())
            removeItemDecoration(object : DividerItemDecoration(this@SearchFeedListActivity, HORIZONTAL) {

            })
            addItemDecoration(spaceDecoration)
        }

        recentKeywordListAdapter.submitList(
            listOf(
                RecentKeywordData(0, "해운대"),
                RecentKeywordData(1, "지리산"),
                RecentKeywordData(2, "을왕리"),
                RecentKeywordData(3, "울릉도"),
                RecentKeywordData(4, "제주도"),
            )
        )
    }

    private fun onClickRecentKeywordItem(keywordData: RecentKeywordData) {
        // TODO 해당 키워드로 검색되도록 하기
        binding.textEditSearchFeed.setText(keywordData.content)
        binding.textEditSearchFeed.setSelection(keywordData.content.length)
    }

    // RecyclerView Item 간 간격 조정하기 위한 클래스
    inner class HorizontalSpaceItemDecoration(private val horizontalSpaceWidth: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val count = state.itemCount

            when (position) {
                0 -> {
                    outRect.left = 0
                }
                else -> {
                    outRect.left = horizontalSpaceWidth
                }
            }
        }
    }

    private fun initPopularKeywordListRecyclerView(recyclerView: RecyclerView) {
        popularKeywordListAdapter = PopularKeywordListAdapter(
            onClick = ::onClickPopularKeywordItem
        )

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = popularKeywordListAdapter

            val spaceDecoration = VerticalSpaceItemDecoration(16f.fromDpToPx())
            removeItemDecoration(object : DividerItemDecoration(this@SearchFeedListActivity, HORIZONTAL) {

            })
            addItemDecoration(spaceDecoration)
        }

        popularKeywordListAdapter.submitList(
            listOf(
                PopularKeywordData(0, "해운대", 55, 1),
                PopularKeywordData(1, "지리산", 45, 2),
                PopularKeywordData(2, "을왕리", 35, 3),
                PopularKeywordData(3, "울릉도", 25, 4),
                PopularKeywordData(4, "제주도", 15, 5)
            )
        )
    }

    // RecyclerView Item 간 간격 조정하기 위한 클래스
    inner class VerticalSpaceItemDecoration(private val verticalSpaceWidth: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val count = state.itemCount

            when (position) {
                0 -> {
                    outRect.bottom = 0
                }
                else -> {
                    outRect.bottom = verticalSpaceWidth
                }
            }
        }
    }

    private fun onClickPopularKeywordItem(keywordData: PopularKeywordData) {
        // TODO 해당 키워드로 검색되도록 하기
        binding.textEditSearchFeed.setText(keywordData.content)
        binding.textEditSearchFeed.setSelection(keywordData.content.length)
    }

    private fun initSearchFeedListRecyclerView(recyclerView: RecyclerView) {
        searchFeedListAdapter = SearchFeedListAdapter(
            onClick = ::onClickSearchedFeed
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return 1 // 1개의 인덱스가 가질 부피
                    }
                }
            }
            adapter = searchFeedListAdapter
        }
    }

    private fun onClickSearchedFeed(feedData: SearchFeedData) {
        // TODO 피드를 클릭하면 상세 페이지로 이동하도록 하기
//        val intent = FeedDetailActivity.getIntent(this@SearchFeedListActivity)
//        startActivity(intent)
    }

    private fun initViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                uiState.collect { state ->
                    when(state) {
                        is SearchFeedListViewModel.UiState.SearchFeedListFailed -> {
                            Toast.makeText(
                                this@SearchFeedListActivity,
                                "검색에 실패했습니다.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is SearchFeedListViewModel.UiState.SearchFeedListSuccess -> {
//                            searchFeedListAdapter.submitList(searchFeedList.value)
                        }
                        is SearchFeedListViewModel.UiState.AddRecentKeywordListFailed -> {
                            Toast.makeText(
                                this@SearchFeedListActivity,
                                "최근 검색어 추가에 실패했습니다.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is SearchFeedListViewModel.UiState.AddRecentKeywordListSuccess -> {
                            recentKeywordListAdapter.submitList(recentKeywordList.value)
                        }
                        is SearchFeedListViewModel.UiState.DeleteRecentKeywordListFailed -> {
                            Toast.makeText(
                                this@SearchFeedListActivity,
                                "최근 검색어를 지우는 데 실패했습니다.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is SearchFeedListViewModel.UiState.DeleteRecentKeywordListSuccess -> {
                            recentKeywordListAdapter.currentList.clear()
                        }
                        is SearchFeedListViewModel.UiState.GetRecentKeywordListFailed -> {
                            Toast.makeText(
                                this@SearchFeedListActivity,
                                "최근 검색어를 불러오는 데 실패했습니다.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is SearchFeedListViewModel.UiState.GetRecentKeywordListSuccess -> {
                            recentKeywordListAdapter.submitList(recentKeywordList.value)
                        }
                        is SearchFeedListViewModel.UiState.GetPopularKeywordListFailed -> {
                            Toast.makeText(
                                this@SearchFeedListActivity,
                                "인기 굳이데이 검색어를 불러오는 데 실패했습니다.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is SearchFeedListViewModel.UiState.GetPopularKeywordListSuccess -> {
                            popularKeywordListAdapter.submitList(popularKeywordList.value)
                        }
                        is SearchFeedListViewModel.UiState.Idle -> {
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvDeleteAllRecentKeywordList.setOnClickListener {
            // TODO: 최근 검색어를 모두 지우도록 구현하기
        }

        binding.layoutSearchFeedList.setOnClickListener {
            closeKeyboard(binding.root)

            if (binding.textEditSearchFeed.hasFocus()) {
                binding.textEditSearchFeed.clearFocus()
            }
        }

        binding.textEditSearchFeed.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val query = textView?.text.toString()
                    binding.rvRecentKeywordList.isGone = true
                    binding.rvPopularKeywordList.isGone = true
                    viewModel.searchPlace(query)
                    Log.d("query", "SearchFeedListActivity - initListeners: query - $query")

                    closeKeyboard(binding.root)
                    binding.textEditSearchFeed.clearFocus()
                }
            }

            true
        }

        binding.ivDelete.setOnClickListener {
            binding.textEditSearchFeed.setText("")
        }
    }

    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        fun getIntent(context: Context) =
            Intent(context, SearchFeedListActivity::class.java)
    }
}