package com.example.questionapp.presentabon.news_screan

import com.example.questionapp.Module.Article

data class NewsScreanState(
    val isLoading:Boolean=false,
    val articals:List<Article> = emptyList(),
    val error:String?=null,
    val isSearchBarVisable:Boolean=false,
    val selectedArtical:Article?=null,
    val category:String="General",
    val searchQuery:String = ""
)
