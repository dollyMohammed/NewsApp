package com.example.questionapp.data.remote

import com.example.questionapp.Module.NewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApp {
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=4bdd8349694747fdb1cbf153330a2d1d

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category:String,
        @Query("country") country:String= "us",
        @Query("apiKey") apiKey:String= API_KEY

    ) : NewResponse
    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query:String,
        @Query("apiKey") apiKey:String= API_KEY

    ) : NewResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY= "4bdd8349694747fdb1cbf153330a2d1d"
    }
}