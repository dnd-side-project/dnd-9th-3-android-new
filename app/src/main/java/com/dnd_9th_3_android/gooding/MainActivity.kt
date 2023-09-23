package com.dnd_9th_3_android.gooding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat
import com.dnd_9th_3_android.gooding.databinding.ActivityMainBinding
import com.dnd_9th_3_android.gooding.trash.FeedFragment
import com.dnd_9th_3_android.gooding.trash.MyGoodingFragment
import com.dnd_9th_3_android.gooding.trash.presentation.gallery.GalleryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavi : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 숨김 대응
        actionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            window.insetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else { // 30 이하 
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        setupBottomNavigationView()
        bottomNavi = binding.bottomNavMain // invisibility 하기 위함
        if (savedInstanceState == null) {
            binding.bottomNavMain.selectedItemId = R.id.menu_feed
        }
        val isOk = intent.getBooleanExtra("isOk", false)
        if(isOk){
            supportFragmentManager.popBackStack()
            bottomNavi.selectedItemId =R.id.menu_my_gooding
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavMain.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val prevFragment = supportFragmentManager.fragments.find {
            it.isVisible
        }

        if (prevFragment != null) {
            supportFragmentManager.beginTransaction().hide(prevFragment).commitNow()
        }

        when (item.itemId) {
            R.id.menu_feed -> {
                val feedFragment = supportFragmentManager.fragments.find { it is FeedFragment }
                if (feedFragment != null) {
                    supportFragmentManager.beginTransaction().show(feedFragment).commit()
                } else {
                    val transaction = supportFragmentManager.beginTransaction()
                        .add(binding.fcvMain.id, FeedFragment.newInstance())

                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
            R.id.menu_record -> {
//                val intent = GalleryActivity.getIntent(this@MainActivity)
//                startActivity(intent)
                if (checkStoragePermission()) {
                    val intent = GalleryActivity.getIntent(this@MainActivity)
                    startActivity(intent)
                }
            }
            R.id.menu_my_gooding -> {
                val myGoodingFragment = supportFragmentManager.fragments.find { it is MyGoodingFragment }
                if (myGoodingFragment != null) {
                    supportFragmentManager.beginTransaction().show(myGoodingFragment).commit()
                } else {
                    val transaction = supportFragmentManager.beginTransaction()
                        .add(binding.fcvMain.id, MyGoodingFragment.newInstance())

                    transaction.addToBackStack(null )
                    transaction.commit()
                }
            }
        }

        return true
    }

    private fun checkStoragePermission(): Boolean {
        val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
        val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

        return if (ActivityCompat.checkSelfPermission(
                this,
                readPermission
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                writePermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(readPermission, writePermission),
                PERMISSION_REQ_CODE
            )
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = GalleryActivity.getIntent(this@MainActivity)
            startActivity(intent)
        }
    }

    companion object {
        const val PERMISSION_REQ_CODE = 1010
    }
}