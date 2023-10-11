package com.dnd_9th_3_android.gooding.login.data.domain

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.google.firebase.auth.FirebaseAuth

interface GoogleLoginInterface {
    // message
    fun toastMessage( message:String)
    // launcher
    fun setLauncher(result: ActivityResult,firebaseAuth:FirebaseAuth,loginCallback:(String?)->Unit)
    // login
    fun login(clientId:String,launcher:ActivityResultLauncher<Intent>)

    fun loginRequest(idToken:String,result:(AccessToken?)->Unit)
}