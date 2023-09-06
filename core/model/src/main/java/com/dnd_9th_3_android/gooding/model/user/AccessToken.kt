package com.dnd_9th_3_android.gooding.model.user

import java.io.Serializable

data class AccessToken(
    val accessToken : String,
    val oauthId : String,
): Serializable