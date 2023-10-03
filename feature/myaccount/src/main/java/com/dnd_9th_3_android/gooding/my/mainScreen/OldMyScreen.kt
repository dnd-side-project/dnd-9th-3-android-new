package com.dnd_9th_3_android.gooding.my.mainScreen

//
//
//@Composable
//fun MyScreen(
//    navController : NavController,
//    bottomNavi : BottomNavigationView
//) {
//    val lazyState = rememberLazyListState()
//    // y축 스크롤
//    var scrolledY = 0f
//    var previousOffset = 0
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        state = lazyState
//    ) {
//        item {
//            Column(
//                modifier = Modifier
//                    .graphicsLayer {
//                        scrolledY += lazyState.firstVisibleItemScrollOffset - previousOffset
//                        translationY = scrolledY * 0.5f
//                        previousOffset = lazyState.firstVisibleItemScrollOffset
//                    }
//                    .wrapContentHeight()
//                    .fillMaxWidth()
//            ) {
//                // main content (top menu)
//
//                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.top_margin)))
//                TopMenuScreen(navController,bottomNavi)
//                LevelScreen(painterResource(id = R.drawable.level_icon), "LV1.초보 낭만러")
//                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_28)))
//                UserInfoScreen(userInfo = SampleUserData.sampleUserData[0])
//                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_24)))
//            }
//        }
//
//        item {
//            // bottom tab
////            BottomTabScreen()
//        }
//    }
//
//
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewMyScreen(){
//    MyScreen(rememberNavController(),BottomNavigationView(LocalContext.current))
//}
