package com.example.questionapp.presentabon.news_screan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questionapp.Module.Article
import com.example.questionapp.Repestory.NewsRepestory
import com.example.questionapp.Util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NewsScreamViewModule @Inject constructor
    (private val newsRepestory: NewsRepestory): ViewModel(

) {
    var state by mutableStateOf(NewsScreanState())
    private var searcjob : Job?= null

   // var articals by mutableStateOf<List<Article>> (emptyList())
    fun onEvent(event: NewsScreanEvent){
        when(event){
            is NewsScreanEvent.onCategoryChanged -> {
                state=state.copy(category = event.category)
                getNewsArticals(state.category)
            }
            is NewsScreanEvent.onNewsCardClicked -> {
                state=state.copy(selectedArtical = event.article)
            }
            NewsScreanEvent.onSearcIconClicked -> {
                state=state.copy(isSearchBarVisable = true, articals = emptyList())

            }


            NewsScreanEvent.onCloseIconClicked -> {
                state=state.copy(isSearchBarVisable = false)
                getNewsArticals(category = state.category)


            }
            is NewsScreanEvent.onSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)

                searcjob?.cancel()
                searcjob=viewModelScope.launch {
                    delay(1000)
                    searchForNews(query = state.searchQuery)
                }
            }
            else -> {}
        }

    }

    private fun getNewsArticals(category: String){

        viewModelScope.launch {
            state= state.copy(
                isLoading = true
            )
            val result= newsRepestory.getTopHeadline(category = category)
            when(result){
                is Resource.Success -> {
                state= state.copy(
                    articals=result.data?: emptyList(),
                    isLoading = false,
                    error = null

                )
                }
                is Resource.Error -> state=state.copy(
                    error = result.message,
                    isLoading = false,
                    articals = emptyList()
                )

                else -> {}
            }
        }
    }
    private fun searchForNews(query: String){
        if (query.isEmpty()){
            return
        }

        viewModelScope.launch {
            state= state.copy(
                isLoading = true
            )
            val result= newsRepestory.searchForNews(query=query)
            when(result){
                is Resource.Success -> {
                    state= state.copy(
                        articals=result.data?: emptyList(),
                        isLoading = false,
                        error = null

                    )
                }
                is Resource.Error -> state=state.copy(
                    error = result.message,
                    isLoading = false,
                    articals = emptyList()
                )

                else -> {}
            }
        }
    }

}