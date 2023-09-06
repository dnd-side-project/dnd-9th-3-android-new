package com.dnd_9th_3_android.gooding.login.data

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoogleLoginImpl @Inject constructor() : GoogleLoginInterface {
    override fun toastMessage(context: Context, message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun setLauncher(
        context: Context,
        result: ActivityResult,
        firebaseAuth: FirebaseAuth,
        loginCallback: (String?) -> Unit
    ) {
        var tokenId: String?
        var email: String

        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)?.let { account ->
                    tokenId = account.idToken
                    if (tokenId != null && tokenId != "") {
                        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)

                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
                                if (firebaseAuth.currentUser != null) {
                                    val user: FirebaseUser = firebaseAuth.currentUser!!
                                    email = user.email.toString()
                                    Log.e(ContentValues.TAG, "email : $email")
                                    val googleSignInToken = account.idToken ?: ""
                                    if (googleSignInToken != "") {
                                        Log.e(ContentValues.TAG, "googleSignInToken : $googleSignInToken")
                                        loginCallback(googleSignInToken)
                                    } else {
                                        toastMessage(context,"null token")
                                        Log.e(ContentValues.TAG, "googleSignInToken이 null")
                                        loginCallback(null)
                                    }
                                }
                            }
                    }else{
                        loginCallback(null)
                    }
                } ?: throw Exception()
            }   catch (e: Exception) {
                e.printStackTrace()
                loginCallback(null)
            }
        }
    }

    override fun login(
        context: Context,
        clientId: String,
        launcher: ActivityResultLauncher<Intent>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientId)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            val signInIntent: Intent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    }

}