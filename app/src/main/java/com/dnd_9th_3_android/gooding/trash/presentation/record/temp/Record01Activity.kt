package com.dnd_9th_3_android.gooding.trash.presentation.record.temp

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.databinding.ActivityRecord01Binding
import com.dnd_9th_3_android.gooding.trash.presentation.record.search.SearchGoodieDayPlaceActivity
import com.dnd_9th_3_android.gooding.trash.presentation.util.fromDpToPx
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class Record01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRecord01Binding
    private lateinit var recordImageVideoListAdapter: RecordImageVideoListAdapter

    private val viewModel: Record01ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecord01Binding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        initView()
        initListeners()

        viewModel.selectedFiles.observe(this) {
            recordImageVideoListAdapter.submitList(it)
            recordImageVideoListAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        binding.progressBar.progress = 333

        initRecyclerView(binding.rvImageVideoFileList)

//        increaseInterval().subscribe {
//            binding.progressBar.progress += it.toInt()
//        }

//        decreaseInterval().subscribe {
//            binding.progressBar.progress += it.toInt()
//        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recordImageVideoListAdapter = RecordImageVideoListAdapter(
            onClick = ::onClickImageVideoItem,
            onClickDelete = ::onClickDeleteImageVideoItem
        )

        recyclerView.run {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = recordImageVideoListAdapter

            val spaceDecoration = HorizontalSpaceItemDecoration(4f.fromDpToPx())
            removeItemDecoration(object : DividerItemDecoration(this@Record01Activity, HORIZONTAL) {

            })
            addItemDecoration(spaceDecoration)
        }
    }

    // RecyclerView Item 간 간격 조정하기 위한 클래스
    inner class HorizontalSpaceItemDecoration(private val horizontalSpaceWidth: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val count = state.itemCount
            outRect.right = horizontalSpaceWidth
        }
    }

    private fun increaseInterval(): Observable<Long> =
        Observable.interval(1L, TimeUnit.MILLISECONDS).map { interval ->
            interval + 1
        }.take(333)

    private fun decreaseInterval(): Observable<Long> =
        Observable.interval(1L, TimeUnit.MILLISECONDS).map { interval ->
            interval - 1
        }.take(333)

    class AnimateProgressBar(
        private var progressBar: ProgressBar,
        private var from: Float,
        private var to: Float
    ) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            val value = from + (to - from) * interpolatedTime
            progressBar.progress = value.toInt()
        }
    }

    private fun onClickImageVideoItem(galleryFileUiData: RecordGalleryItem) {

    }

    private fun onClickDeleteImageVideoItem(galleryFileUiData: RecordGalleryItem) {
        viewModel.removeGalleryFileUiData(galleryFileUiData)
    }

    private fun initListeners() {
        binding.ivArrowBack.setOnClickListener {
            finish()
        }

        binding.layoutEditGoodieDay.setOnClickListener {
            closeKeyboard(binding.root)

            if (binding.textLayoutTitleGoodieDay.hasFocus()) {
                binding.textLayoutTitleGoodieDay.clearFocus()
            }

            if (binding.textLayoutContentGoodieDay.hasFocus()) {
                binding.textLayoutContentGoodieDay.clearFocus()
            }
        }

        binding.textLayoutTitleGoodieDay.apply {
            setHint(getString(R.string.please_write_title))
        }

        binding.textLayoutContentGoodieDay.apply {
            setHint(getString(R.string.please_write_your_goodie_day))
        }

        binding.textLayoutDateGoodieDay.setOnClickListener {
            val bottomSheetDatePicker = BottomSheetDatePicker.newInstance()
            bottomSheetDatePicker.show(supportFragmentManager, bottomSheetDatePicker.tag)
        }

        binding.cvPlaceGoodieDay.setOnClickListener {
            val intent = SearchGoodieDayPlaceActivity.getIntent(this@Record01Activity)
            startActivity(intent)
        }

        binding.cvCategoryGoodieDay.setOnClickListener {
//            val bottomSheetSelectCategory = BottomSheetSelectCategory.newInstance()
//            bottomSheetSelectCategory.show(supportFragmentManager, bottomSheetSelectCategory.tag)
        }

        binding.switchPrivacySetting.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {

            } else {

            }
        }

        binding.btnNextStep.setOnClickListener {
            val intent = Record02Activity.getIntent(this@Record01Activity)
            startActivity(intent)
        }


        supportFragmentManager.setFragmentResultListener(
            BottomSheetDatePicker.REQUEST_KEY,
            this
        ) { requestKey, bundle ->
            if (requestKey == BottomSheetDatePicker.REQUEST_KEY) {
                val date = bundle.getLong(BottomSheetDatePicker.Result.DATE)

                binding.textEditDateGoodieDay.text = SimpleDateFormat("yy / MM / dd").format(date)
                binding.textEditDateGoodieDay.setTextColor(getColor(R.color.white))
                Log.d("TAG", "initListeners: $date")
            }
        }


//            BottomSheetSelectCategory.REQUEST_KEY,
//            this
//        ) { requestKey, bundle ->
//            if (requestKey == BottomSheetSelectCategory.REQUEST_KEY) {
//                val category = bundle.getString(BottomSheetSelectCategory.Result.CATEGORY)
//
//                binding.tvChooseCategoryGoodieDay.text = category
//                binding.tvChooseCategoryGoodieDay.setTextColor(getColor(R.color.white))
//                Log.d("TAG", "initListeners: $category")
//            }
//        }

    }

    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

//    companion object {
//        fun getIntent(context: Context, selectedFiles: List<GalleryFileUiData>) =
//            Intent(context, Record01Activity::class.java).apply {
//                putExtra(Record01ViewModel.KEY_SELECTED_FILES, selectedFiles.toTypedArray())
//            }
//    }
}