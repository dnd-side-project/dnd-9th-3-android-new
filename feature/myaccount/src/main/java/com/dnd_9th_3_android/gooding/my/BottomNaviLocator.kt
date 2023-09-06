package com.dnd_9th_3_android.gooding.my

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

object BottomNaviLocator {
    fun stateChange(
        bottomNavi: BottomNavigationView,
        boolean: Boolean
    ){
        if (boolean){
            if (bottomNavi.visibility == View.GONE)
                bottomNavi.visibility = View.VISIBLE
        }else{
            if (bottomNavi.visibility == View.VISIBLE){
                bottomNavi.visibility = View.GONE
            }
        }
    }
}