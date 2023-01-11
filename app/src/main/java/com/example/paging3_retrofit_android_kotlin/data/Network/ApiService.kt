package com.example.paging3_retrofit_android_kotlin.data.Network

import com.example.paging3_retrofit_android_kotlin.data.Dogs
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASE_URL = "https://api.thedogapi.com"
    }

    @GET("v1/images/search")
    suspend fun getAllDogs(
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ):List<Dogs>
}