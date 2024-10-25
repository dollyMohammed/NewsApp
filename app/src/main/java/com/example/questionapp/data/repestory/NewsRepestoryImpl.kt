package com.example.questionapp.data.repestory

import com.example.questionapp.Module.Article
import com.example.questionapp.Repestory.NewsRepestory
import com.example.questionapp.Util.Resource
import com.example.questionapp.data.remote.NewsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class NewsRepestoryImpl(
    private val newsApp: NewsApp
): NewsRepestory{
    override suspend fun getTopHeadline(category: String): Resource<List<Article>> {
        return try {
            val response = newsApp.getBreakingNews(category = category)
            Resource.Success(response.articles)
        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<Article>> {
        return try {
            val response = newsApp.searchForNews(query=query)
            Resource.Success(response.articles)
        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }


    }
}