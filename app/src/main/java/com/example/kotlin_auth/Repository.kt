package com.example.kotlin_auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.kotlin_auth.api.API
import com.example.kotlin_auth.api.AuthRequestParams
import com.example.kotlin_auth.api.RegistrationRequestParams


object Repository {

    // Ленивое создание Retrofit экземпляра
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://kotlin-android-auth.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Ленивое создание API
    private val API: API by lazy {
        retrofit.create(com.example.kotlin_auth.api.API::class.java)
    }

    suspend fun authenticate(login: String, password: String) = API.authenticate(
        AuthRequestParams(login, password)
    )

    suspend fun register(login: String, password: String) =
        API.register(
            RegistrationRequestParams(
                login,
                password
            )
        )
}
