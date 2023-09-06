package com.dnd_9th_3_android.gooding.presentation.record.search

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnd_9th_3_android.gooding.data.model.map.KakaoMapDocuments
import com.dnd_9th_3_android.gooding.databinding.ActivitySearchGoodieDayPlaceBinding
import com.dnd_9th_3_android.gooding.databinding.ItemKakaoMapPlaceBinding
import com.dnd_9th_3_android.gooding.presentation.record.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedkeyboardobserver.TedKeyboardObserver
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SearchGoodieDayPlaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchGoodieDayPlaceBinding
    private lateinit var goodieDayPlaceListAdapter: GoodieDayPlaceListAdapter
    private val viewModel by viewModels<SearchGoodieDayPlaceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchGoodieDayPlaceBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
        initViewModel()
        initListeners()
    }

    private fun initView() {
        initRecyclerView(binding.rvPlaceList)

        if (binding.textEditSearchPlace.hasFocus()) {
            binding.ivDelete.isGone = false
            binding.ivDelete.isVisible = true
        } else {
            binding.ivDelete.isGone = true
        }

        TedKeyboardObserver(this)
            .listen { isShow ->
                val isItemEmpty = goodieDayPlaceListAdapter.itemCount == 0
//                binding.tvNothingFound.isVisible = !isShow && isItemEmpty

                if (!isShow) {
                    binding.textEditSearchPlace.clearFocus()
                }
            }
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        goodieDayPlaceListAdapter = GoodieDayPlaceListAdapter(
            onClick = ::onClickPlaceItem
        )

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = goodieDayPlaceListAdapter
        }
    }
    private fun onClickPlaceItem(kakaoMapDocuments: KakaoMapDocuments) {
        finish()
    }

    private fun initViewModel() {
        with(viewModel) {
            lifecycleScope.launch {
                uiState.collect { state ->
                    when (state) {
                        is SearchGoodieDayPlaceViewModel.UiState.SearchPlaceSuccess -> {
                            val mapAddressList = state.mapAddressList
                            binding.tvPleaseSearchPlace.isVisible = mapAddressList.isEmpty()
                            goodieDayPlaceListAdapter.submitList(mapAddressList)
                        }

                        is SearchGoodieDayPlaceViewModel.UiState.SearchPlaceFailed -> {
                            Toast.makeText(
                                this@SearchGoodieDayPlaceActivity,
                                "주소 검색에 실패했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is SearchGoodieDayPlaceViewModel.UiState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.layoutSearchPlace.setOnClickListener {
            closeKeyboard(binding.root)

            if (binding.textEditSearchPlace.hasFocus()) {
                binding.textEditSearchPlace.clearFocus()
                binding.ivDelete.isGone = true
            }
        }

        binding.textEditSearchPlace.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val query = textView?.text.toString()
                    viewModel.searchPlace(query)
                    Log.d("query", "SearchGoodieDayPlaceActivity - initListeners: query - $query")

                    closeKeyboard(binding.root)
                    binding.textEditSearchPlace.clearFocus()
                }
            }

            true
        }

        binding.ivSearch.setOnClickListener {
            val query = binding.textEditSearchPlace.text.toString()
            viewModel.searchPlace(query)
            Log.d("query", "SearchGoodieDayPlaceActivity - initListeners: query - $query")

            closeKeyboard(binding.root)
            binding.textEditSearchPlace.clearFocus()
        }

        binding.ivDelete.setOnClickListener {
            binding.textEditSearchPlace.text?.clear()
        }
    }

    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        fun getIntent(context: Context,) =
            Intent(context, SearchGoodieDayPlaceActivity::class.java)
    }
}













// 카카오맵 api
object KakaoMapApiClient {
    private var instance: Retrofit? = null
    private const val CONNECT_TIMEOUT_SEC = 20000L
    private const val BASE_URL = "https://dapi.kakao.com/"

    fun getInstance(): Retrofit {
        if (instance == null) {

            // 로깅인터셉터 세팅
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            // OKHttpClient에 로깅인터셉터 등록
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return instance!!
    }
}

// api 통신을 위한 retrofit
private val retrofit: Retrofit = KakaoMapApiClient.getInstance()

// kakao map API Key
private const val API_KEY = "66e15e5cd7fdce2de67e28ec53aad52a"

//suspend fun getKakaoMapAddress(
//    keyword: String,
//    addPlaceList: (item: SelectPlaceItem) -> Unit
//) {
//    retrofit.create(KakaoMapService::class.java)
//        .getKakaoMapAddress(keyword)
//        .enqueue(object : Callback<KakaoMapData> {
//            override fun onResponse(call: Call<KakaoMapData>, response: Response<KakaoMapData>) {
//                Log.d(ContentValues.TAG, "카카오 주소 조회 결과 -------------------------------------------")
//                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")
//
//                val body: KakaoMapData = response.body()!!
//                val placeList = body.documents
//                for(i in 0 until placeList.size) {
//                    var selected = false
//                    // 첫 번째 주소 선택
//                    if(i == 0) selected = true
//
//                    // 주소 선택지 추가
//                    addPlaceList(
//                        SelectPlaceItem(
//                            placeList[i].placeName,
//                            placeList[i].roadAddressName,
//                            placeList[i].addressName,
//                            selected
//                        )
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<KakaoMapData>, t: Throwable) {
//                Log.d(ContentValues.TAG, "카카오 주소 조회 결과 fail -------------------------------------------")
//                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
//            }
//        })
//}