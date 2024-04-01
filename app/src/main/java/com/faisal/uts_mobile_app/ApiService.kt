package com.faisal.uts_mobile_app

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<UserListResponse>
}