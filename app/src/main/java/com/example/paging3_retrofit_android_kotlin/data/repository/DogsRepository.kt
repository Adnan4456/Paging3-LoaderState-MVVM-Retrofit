package com.example.paging3_retrofit_android_kotlin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging3_retrofit_android_kotlin.data.Network.ApiService
import javax.inject.Inject

class DogsRepository
    @Inject constructor(
    private val apiService: ApiService
) {

    fun fetchDogs() = Pager(
        pagingSourceFactory = {DogsPageSource(apiService)},
        config = PagingConfig(pageSize = 20, maxSize = 100)
    ).flow

}