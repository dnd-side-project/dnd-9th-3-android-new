package com.dnd_9th_3_android.gooding.api

import android.content.Context
import android.content.SharedPreferences

class TokenSharedPreferences(context : Context) {
    private val prefsFilename = "token_prefs"
    private val accessTokenKey = "accessToken"
    private val accessTokenTimeDateKey = "accessTokenTimeDate"
    private val refreshTokenKey = "refreshToken"
    private val userOauthKey = "userOauth"
    private val prefs : SharedPreferences = context.getSharedPreferences(prefsFilename,0)

    var accessToken : String?
        get() = prefs.getString(accessTokenKey,"")
        set(value) = prefs.edit().putString(accessTokenKey,value).apply()

    var accessTokenTimeDate : Int
        get() = prefs.getInt(accessTokenTimeDateKey,0)
        set(value) = prefs.edit().putInt(accessTokenTimeDateKey,value).apply()

    var refreshToken : String?
        get() = prefs.getString(refreshTokenKey,"")
        set(value) = prefs.edit().putString(refreshTokenKey,value).apply()

    var userOauth : String?
        get() = prefs.getString(userOauthKey,"")
        set(value) = prefs.edit().putString(userOauthKey,value).apply()
}