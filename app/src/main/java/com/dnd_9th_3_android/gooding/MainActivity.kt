package com.dnd_9th_3_android.gooding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dnd_9th_3_android.gooding.common.navi.MainNaviHost
import com.dnd_9th_3_android.gooding.common.state.ManageBottomBarState
import com.dnd_9th_3_android.gooding.common.state.rememberApplicationState
import com.dnd_9th_3_android.gooding.databinding.ActivityMainBinding
import com.dnd_9th_3_android.gooding.trash.FeedFragment
import com.dnd_9th_3_android.gooding.trash.MyGoodingFragment
import com.dnd_9th_3_android.gooding.trash.presentation.gallery.GalleryActivity
import com.dnd_9th_3_android.gooding.ui.theme.GoodingTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoodingTheme {
                val appState = rememberApplicationState()
                val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
                ManageBottomBarState(
                    navBackStackEntry = navBackStackEntry,
                    bottomBarState = appState.bottomBarState
                )
                MainNaviHost(appState = appState)
            }
        }
    }
}