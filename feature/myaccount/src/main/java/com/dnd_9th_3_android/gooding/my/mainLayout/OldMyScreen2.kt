package com.dnd_9th_3_android.gooding.my.mainLayout

//@Composable
//fun OldMyScreen2(
//    navController: NavController,
//    bottomNavi: BottomNavigationView
//) {
//    var offset by remember { mutableStateOf(0f) }
//    var isVisibleTop by remember { mutableStateOf(true) }
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        if (isVisibleTop) {
//            Column(
//                modifier = Modifier.graphicsLayer {
//                    if (offset<0f) {
//                        translationY = offset * 0.5f
//                    }
//                }
//            ) {
//                // main content (top menu)
//                Spacer(modifier = Modifier.height(dimensionResource(id = com.dnd_9th_3_android.gooding.feature.my.R.dimen.top_margin)))
//                TopMenuScreen(navController, bottomNavi)
//                LevelScreen(
//                    painterResource(id = R.drawable.level_icon),
//                    "LV1.초보 낭만러"
//                )
//                Spacer(modifier = Modifier.height(dimensionResource(id = com.dnd_9th_3_android.gooding.feature.my.R.dimen.padding_28)))
//                UserInfoScreen(userInfo = SampleUserData.sampleUserData[0])
//                Spacer(modifier = Modifier.height(dimensionResource(id = com.dnd_9th_3_android.gooding.feature.my.R.dimen.padding_24)))
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .graphicsLayer {
//                    if (isVisibleTop) {
//                        if (offset < 0f) {
//                            translationY = offset * 0.5f
//                        }
//                    }
//                    if (offset < -2500f) {
//                        isVisibleTop = false
//                        bottomNavi.visibility = View.GONE
//                    }
//                }
//                .scrollable(
//                    orientation = Orientation.Vertical,
//                    state = rememberScrollableState { delta ->
//                        offset += delta
//                        delta
//                    }
//                )
//            ,
//        ) {
//            BottomTabScreen(isVisibleTop, setMaxScreen = {
//                isVisibleTop = true
//                bottomNavi.visibility = View.VISIBLE
//                offset = 0f
//            })
//        }
//    }
//
//}