package com.dnd_9th_3_android.gooding.presentation.gallery

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.FeedFragment
import com.dnd_9th_3_android.gooding.MyGoodingFragment
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ActivityGalleryBinding
import com.dnd_9th_3_android.gooding.presentation.record.RecordActivity
import com.dnd_9th_3_android.gooding.presentation.record.temp.Record01Activity
import com.dnd_9th_3_android.gooding.presentation.util.fromDpToPx
import com.dnd_9th_3_android.gooding.ui.component.CenterToastView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

const val MAX_SELECT_IMAGE_COUNT = 5

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    private lateinit var galleryFileListAdapter: GalleryFileListAdapter
    private lateinit var galleryAlbumListAdapter: GalleryAlbumListAdapter

    private val viewModel by viewModels<GalleryViewModel>()

    private var currentOpenedFragment: String = FEED_FRAGMENT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
//        binding = ActivityGalleryBinding.inflate(layoutInflater)
//        val view = binding.root
//
//        setContentView(view)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initView()
        initViewModel()
        initListeners()

        if (savedInstanceState != null) {
            currentOpenedFragment =
                savedInstanceState.getString(LAST_OPENED_FRAGMENT_REF).toString()
        }
    }

    private fun initView() {
        initGalleryFileListRecyclerView(binding.rvGalleryImage)
        initAlbumRecyclerView()

        binding.llCurrentAlbumName.setOnClickListener {
            binding.rvGalleryAlbumList.isVisible = !binding.rvGalleryAlbumList.isVisible

            if (binding.rvGalleryAlbumList.isVisible) {
                galleryAlbumListAdapter.submitList(viewModel.getAlbumList())
                binding.ivArrowDown.setImageResource(R.drawable.icon_arrow_up)
            } else {
                galleryAlbumListAdapter.submitList(emptyList())
                binding.ivArrowDown.setImageResource(R.drawable.icon_arrow_down)
            }
        }
    }

    private fun initAlbumRecyclerView() {
        galleryAlbumListAdapter = GalleryAlbumListAdapter(
            onClick = ::onClickAlbumItem
        )

        binding.rvGalleryAlbumList.layoutManager = LinearLayoutManager(this)
        binding.rvGalleryAlbumList.adapter = galleryAlbumListAdapter
    }

    private fun initGalleryFileListRecyclerView(recyclerView: RecyclerView) {
        galleryFileListAdapter = GalleryFileListAdapter(
            onClick = ::onClickGalleryImageItem,
            isFullSelected = {
                viewModel.selectedItems.size >= MAX_SELECT_IMAGE_COUNT
            }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return 1 // 1개의 인덱스가 가질 부피
                    }
                }
            }
            adapter = galleryFileListAdapter
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = 3,
                    sideSpacing = 1f.fromDpToPx(),
                    bottomSpacing = 2f.fromDpToPx()
                )
            )
        }
    }

    private fun onClickGalleryImageItem(galleryFileUiData: GalleryFileUiData) {
        val isSuccess = viewModel.selectedGalleryImageItem(galleryFileUiData)
        if (isSuccess) {
            galleryFileListAdapter.notifyDataSetChanged()
        } else {
            showToast()
        }

        if (viewModel.selectedItems.isNotEmpty()) {
            binding.tvNextStep.isEnabled = true
            binding.tvNextStep.setTextColor(
                ContextCompat.getColor(
                    this@GalleryActivity,
                    R.color.primaryColor
                )
            )
        } else {
            binding.tvNextStep.isEnabled = false
            binding.tvNextStep.setTextColor(
                ContextCompat.getColor(
                    this@GalleryActivity,
                    com.dnd_9th_3_android.gooding.core.data.R.color.blue_gray_3
                )
            )
        }
    }

    internal class GridSpacingItemDecoration(
        private val spanCount: Int, // Grid의 column 수
        private val sideSpacing: Int, // 간격
        private val bottomSpacing: Int
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position: Int = parent.getChildAdapterPosition(view)

            outRect.left = sideSpacing
            outRect.right = sideSpacing
            outRect.bottom = bottomSpacing
        }
    }

    private fun showToast() {
        val toastView = CenterToastView(this@GalleryActivity)
        toastView.setText(getString(R.string.limit_exceed_toast))

        toastView.layoutParams = ConstraintLayout.LayoutParams(
            resources.getDimensionPixelOffset(R.dimen.center_toast_width),
            resources.getDimensionPixelOffset(R.dimen.center_toast_height)
        ).apply {
            startToStart = binding.rootView.id
            topToTop = binding.rootView.id
            endToEnd = binding.rootView.id
            bottomToBottom = binding.rootView.id
        }

        binding.rootView.addView(toastView)

        lifecycleScope.launch {
            delay(2.seconds)
            binding.rootView.removeView(toastView)
        }
    }

    private fun onClickAlbumItem(galleryAlbumUiData: GalleryAlbumUiData) {
        val folderName = galleryAlbumUiData.folderName
        if (binding.tvTitleAlbumType.text == folderName) {
            return
        }
        binding.tvTitleAlbumType.text = folderName
        viewModel.updateAlbumName(folderName)
        binding.rvGalleryAlbumList.isGone = true
        viewModel.resetSelectedItems()
    }

    private fun initViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                uiState.collect { state ->
                    when (state) {
                        is GalleryViewModel.UiState.GetGalleryFileListSuccess -> {

                        }

                        is GalleryViewModel.UiState.GetGalleryFileListFailed -> {
                            Toast.makeText(
                                this@GalleryActivity,
                                "이미지 목록을 불러오는 데 실패했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is GalleryViewModel.UiState.Idle -> {}
                    }
                }
            }

            lifecycleScope.launch {
                imagePagingList.collect { data ->
                    galleryFileListAdapter.submitData(data)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_OPENED_FRAGMENT_REF, currentOpenedFragment);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun initListeners() {
        binding.ivArrowBack.setOnClickListener {
            finish()

            val prevFragment = supportFragmentManager.fragments.find {
                it.isVisible
            }
//            supportFragmentManager.popBackStack(null, 0)
//            supportFragmentManager.beginTransaction().replace(R.id.fcv_main, prevFragment!!).commit()
        }

        binding.ivArrowDown.setOnClickListener {

        }

        binding.tvNextStep.setOnClickListener {
            val intent = RecordActivity.getIntent(this@GalleryActivity, viewModel.selectedItems)
            startActivity(intent)
        }
    }

    private fun initFragmentType(type: String): Fragment {
        return when (type) {
            FEED_FRAGMENT -> {
                FeedFragment.newInstance()
            }

            MY_GOODING_FRAGMENT -> {
                MyGoodingFragment.newInstance()
            }

            else -> {
                throw IllegalArgumentException("There is no type: $type");
            }
        }
    }

    companion object {
        const val LAST_OPENED_FRAGMENT_REF = "LAST_OPENED_FRAGMENT_REF"
        const val FEED_FRAGMENT = "FEED_FRAGMENT"
        const val MY_GOODING_FRAGMENT = "MY_GOODING_FRAGMENT"

        fun getIntent(context: Context) =
            Intent(context, GalleryActivity::class.java)
    }
}