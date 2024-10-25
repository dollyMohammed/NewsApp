package com.example.questionapp.presentabon.news_screan

import com.example.questionapp.Module.Article
import retrofit2.http.Query

sealed class NewsScreanEvent{
    data class onNewsCardClicked(val article: Article) : NewsScreanEvent()
    data class onCategoryChanged(val category: String) : NewsScreanEvent()
    data class onSearchQueryChanged(val searchQuery: String) : NewsScreanEvent()
    object onSearcIconClicked :NewsScreanEvent()
    object onCloseIconClicked :NewsScreanEvent()

}
