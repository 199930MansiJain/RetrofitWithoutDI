package com.example.retrofitwithoutdi.network

import com.example.retrofitwithoutdi.model.ReposnseList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String): ReposnseList
}