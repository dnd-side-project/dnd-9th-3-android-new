package com.dnd_9th_3_android.gooding.my.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.data.state.SwipingStates
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.my.subLayout.BottomTabScreen
import com.dnd_9th_3_android.gooding.my.subLayout.LevelScreen
import com.dnd_9th_3_android.gooding.my.subLayout.TopMenuScreen
import com.dnd_9th_3_android.gooding.my.subLayout.UserInfoScreen
import com.dnd_9th_3_android.gooding.my.tabTop.ui.MainTopClickScreen
import com.dnd_9th_3_android.gooding.my.tabTop.ui.MainTopScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel
import kotlinx.coroutines.*


//https://github.com/Debdutta-Panda/MotionLayoutWithNestedScrollAndSwipeable/blob/main/app/src/main/java/com/debduttapanda/motionlayoutwithnestedscrollandswipeable/MainActivity.kt
@OptIn(ExperimentalMaterialApi::class, ExperimentalMotionApi::class, DelicateCoroutinesApi::class)
@Composable
fun MyScreen(
    viewModel: MyOptionViewModel = hiltViewModel()
) {
    // swipe state
    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)
    // top visible
    var topBottom by remember { mutableStateOf(false) }
    BoxWithConstraints( //to get the max height
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        val heightInPx = with(LocalDensity.current) { maxHeight.toPx() }
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                // pre scroll
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipingState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                // post scroll
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                // Fling
                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        // root container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.05f) //general
                    },
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to SwipingStates.COLLAPSED, // min height - COLLAPSED
                        heightInPx to SwipingStates.EXPANDED // max height - EXPANDED
                    )
                )
                .nestedScroll(nestedScrollConnection)
        ) {
            // swipe progress
            val computedProgress by remember {
                derivedStateOf {
                    if (swipingState.progress.to == SwipingStates.COLLAPSED) {
                        swipingState.progress.fraction
                    } else {
                        1f - swipingState.progress.fraction
                    }
                }
            }

            // main content (top menu)
            MainTopScreen()

            // motion layout - constrain layout
            MotionLayout(
                modifier = Modifier.fillMaxSize(),
                start = ConstraintSet {
                    val header = createRefFor("header")
                    val body = createRefFor("body")
                    constrain(header) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.value(401.dp)
                    }
                    constrain(body) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.fillToConstraints
                        this.top.linkTo(header.bottom, 0.dp)
                        this.bottom.linkTo(parent.bottom, 0.dp)
                    }
                },
                end = ConstraintSet {
                    val header = createRefFor("header")
                    val body = createRefFor("body")
                    constrain(header) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.value(0.dp)
                    }
                    constrain(body) {
                        this.width = Dimension.matchParent
                        this.height = Dimension.fillToConstraints
                        this.top.linkTo(header.bottom, 0.dp)
                        this.bottom.linkTo(parent.bottom, 0.dp)
                    }
                    // top bottom Visible
                    if (swipingState.currentValue == SwipingStates.COLLAPSED) {
                        topBottom = true
                        viewModel.applicationState?.bottomBarState?.value = false
                    } else {
                        topBottom = false
                        viewModel.applicationState?.bottomBarState?.value = true
                    }
                },
                progress = computedProgress
            ) {
                // id - header view (클릭 이벤트 용 샘플 뷰 )
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .layoutId("header")
                        .fillMaxWidth()
                        .wrapContentHeight()
                ){
                    // click content (top menu)
                    MainTopClickScreen()
                }
                // id - body view
                Box(
                    modifier = Modifier
                        .layoutId("body")
                        .fillMaxWidth()
                ) {
                    BottomTabScreen(topBottom, setMaxScreen = {
                        topBottom = false
                        GlobalScope.launch(Dispatchers.Main) {
                            swipingState.snapTo(SwipingStates.EXPANDED)
                        }
                    })
                }
            }
        }
    }
}

