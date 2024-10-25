package com.example.questionapp.Repestory

import com.example.questionapp.Module.Article
import com.example.questionapp.Util.Resource

interface NewsRepestory {
    suspend fun getTopHeadline(
        category: String
    ):Resource<List<Article>>
    suspend fun searchForNews(
        query: String
    ):Resource<List<Article>>

}