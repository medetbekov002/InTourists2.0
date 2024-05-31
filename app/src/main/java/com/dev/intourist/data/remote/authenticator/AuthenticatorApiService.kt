package com.dev.intourist.data.remote.authenticator

import com.dev.intourist.data.remote.authenticator.RefreshToken
import com.dev.intourist.data.remote.authenticator.Tokens
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticatorApiService {

    @POST("/api/refreshtoken")
    fun refreshToken(@Body refreshToken: RefreshToken): Call<Tokens>
}