package com.dnd_9th_3_android.gooding.common.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardMedium

// BottomNavigation Bar
@Composable
fun BottomBar(
    appState : ApplicationState,
    bottomNaviItems : List<BottomScreen> = BottomScreen.BOTTOM_NAV_ITEMS,
) {
    AnimatedVisibility(
        visible = appState.bottomBarState.value,
        modifier = Modifier
            .background(colorResource(id = R.color.blue_gray_7))
            .fillMaxWidth()
            .height(70.dp)
    ) {
        BottomNavigation(
            backgroundColor = (colorResource(id = R.color.blue_gray_7))
        ) {
            val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            bottomNaviItems.forEachIndexed { _, screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any {it.route == screen.route} == true
                BottomNavigationItem(

                    icon = {
                        Icon(
                            painter = painterResource(
                                id =
                                if (isSelected) screen.selectedDrawableResId else screen.drawableResId
                            ),
                            contentDescription = null,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(screen.stringResId),
                            fontFamily = pretendardMedium,
                            fontSize = 10.sp,
                            color = colorResource(id =
                            if (isSelected) R.color.white else  R.color.blue_gray_4
                            )
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        appState.navController.navigate(screen.route){
                            // 레코드 스크린의 경우 백 스택 추가
                            if (screen.route!=ScreenRoot.MAIN_RECORD) {
                                // saveState +  restoreState -> 상태 저장, 유지 , 복원
                                popUpTo(ScreenRoot.MAIN_GRAPH) {
                                    saveState = true
                                }
                                // 멀티 목적지 방지
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = colorResource(id = R.color.blue_gray_4)
                )
            }
        }
    }
}