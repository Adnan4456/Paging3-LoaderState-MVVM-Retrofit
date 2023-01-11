package com.example.paging3_retrofit_android_kotlin.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3_retrofit_android_kotlin.data.Dogs
import com.example.paging3_retrofit_android_kotlin.data.Network.ApiService
import retrofit2.HttpException
import java.io.IOException

class DogsPageSource
constructor(private val apiService: ApiService) : PagingSource<Int,Dogs>() {

    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dogs> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiService.getAllDogs(page,params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dogs>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}