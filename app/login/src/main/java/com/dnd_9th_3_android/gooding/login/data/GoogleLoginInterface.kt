package com.dnd_9th_3_android.gooding.login.data

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth

interface GoogleLoginInterface {
    // message
    fun toastMessage(context: Context, message:String)
    // launcher
    fun setLauncher(context: Context,result: ActivityResult,firebaseAuth:FirebaseAuth,loginCallback:(String?)->Unit)
    // login
    fun login(context: Context,clientId:String,launcher:ActivityResultLauncher<Intent>)
}