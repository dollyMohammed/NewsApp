package com.example.questionapp.Module

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)