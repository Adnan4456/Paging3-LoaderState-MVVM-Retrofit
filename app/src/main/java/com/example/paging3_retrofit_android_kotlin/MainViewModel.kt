package com.example.paging3_retrofit_android_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3_retrofit_android_kotlin.data.Dogs
import com.example.paging3_retrofit_android_kotlin.data.Network.ApiService
import com.example.paging3_retrofit_android_kotlin.data.repository.DogsPageSource
import com.example.paging3_retrofit_android_kotlin.data.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject constructor
    (
//    private val apiService: ApiService,
     private val repository: DogsRepository
    )
    :ViewModel(){

//    private var currentSearchResult: Flow<PagingData<Dogs>>? = null

    fun searchRepos() : Flow<PagingData<Dogs>>{
        return repository.fetchDogs()
            .cachedIn(viewModelScope)
    }


//    val getAllDogs: Flow<PagingData<Dogs>> = Pager(
//        config = PagingConfig(20,enablePlaceholders = false)
//    ){
//        DogsPageSource(apiService)
//    }.flow.cachedIn(viewModelScope)


}