package com.dnd_9th_3_android.gooding.login.data.repository

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.UserInfoSharedPreferences
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.UserData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GoogleLoginImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkManager: NetworkManager
) : GoogleLoginInterface {
    override fun toastMessage( message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun setLauncher(
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
                                        toastMessage("null token")
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
        }else { loginCallback(null) }
    }

    override fun login(
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

    override fun loginRequest(idToken:String,result:(AccessToken?)->Unit) {
        networkManager.getLoginApiService()
            .loginGoogle(idToken)
            .enqueue(object : Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                    result(response.body())
                }
                override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                    result(null)
                }

            })
    }


}