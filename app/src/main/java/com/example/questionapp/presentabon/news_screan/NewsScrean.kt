package com.example.questionapp.presentabon.news_screan

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ModalBottomSheet
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.questionapp.Module.Article
import com.example.questionapp.component.BottomSheetContent
import com.example.questionapp.component.CategortTabRow
import com.example.questionapp.component.NewsArticalCard
import com.example.questionapp.component.NewsScreanTopBar
import com.example.questionapp.component.RetryCount
import com.example.questionapp.component.SearchAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun NewsScrean(
   // viewModule: NewsScreamViewModule = hiltViewModel()

      state: NewsScreanState,
    onEvent:(NewsScreanEvent) -> Unit,
      onRedFullStoryButtonClicked : (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 7 }
    )
    val coroutainScope = rememberCoroutineScope()
    val categerious = listOf(
        "General", " Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    var shouldButtonSheetShow by remember {
        mutableStateOf(false)
    }
    val focusRequester = remember {


        FocusRequester()
}
    val focusManager= LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    if (shouldButtonSheetShow){
        ModalBottomSheet(onDismissRequest = { shouldButtonSheetShow=false },
            //sheetState= sheetState,
            content = {
                state.selectedArtical?.let {
                    BottomSheetContent(article = it, onReedFullStoryButtonClicked = {
                        onRedFullStoryButtonClicked(it.url)
                        coroutainScope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) shouldButtonSheetShow = false
                        }
                    })



                }

            }

        )



    }
    LaunchedEffect(key1 = pagerState ){
        snapshotFlow { pagerState.currentPage }.collect{page ->
            onEvent(NewsScreanEvent.onCategoryChanged(category = categerious[page]))

        }
    }
    LaunchedEffect(key1 = pagerState ){
        snapshotFlow { pagerState.currentPage }.collect{page ->
            onEvent(NewsScreanEvent.onCategoryChanged(category = categerious[page]))

        }
    }
LaunchedEffect(key1 = Unit){
    if (state.searchQuery.isNotEmpty()){
        onEvent(NewsScreanEvent.onSearchQueryChanged(searchQuery = state.searchQuery))
    }
}
    Column (modifier = Modifier.fillMaxSize()){
        Crossfade(targetState = state.isSearchBarVisable) { isVisable ->
            if (isVisable) {
                Column {
                SearchAppBar(
                   modifier = Modifier.focusRequester(focusRequester) ,
                    value = state.searchQuery,
                    onInputeValueChange = {newValue ->
                        onEvent(NewsScreanEvent.onSearchQueryChanged(newValue))

                    },
                    onClosedIconClicked = {
                        onEvent(NewsScreanEvent.onCloseIconClicked)
                    },
                    onSearchIconClicked = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    })
                NewsArticlesList(state = state, onCardClicked = { article ->
                    shouldButtonSheetShow = true
                    onEvent(NewsScreanEvent.onNewsCardClicked(article = article))
                },
                    onRetry = {
                        onEvent(NewsScreanEvent.onCategoryChanged(state.category))
                    })
            }




            }else{
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        NewsScreanTopBar (
                            scrollBehavior=scrollBehavior,
                            onSearchIconClicked = {
                                coroutainScope.launch {
                                    delay(500)
                                    focusRequester.requestFocus()
                                }
                                onEvent(NewsScreanEvent.onSearcIconClicked)
                            })
                    }
                ) { Padding ->
                    Column (modifier = Modifier
                        .fillMaxSize()
                        .padding(Padding)){
                        CategortTabRow(pagerState = pagerState, categories = categerious,
                            onTabSelected = { index ->
                                coroutainScope.launch { pagerState.animateScrollToPage(index) }

                            })
                        HorizontalPager(state = pagerState,
                            //pageCount= categerious.size

                        )
                        {
                            NewsArticlesList(state = state, onCardClicked = { article ->
                                shouldButtonSheetShow=true
                                onEvent(NewsScreanEvent.onNewsCardClicked(article = article))
                            },
                                onRetry = {
                                    onEvent(NewsScreanEvent.onCategoryChanged(state.category))
                                })



                        }


                    }


                }


            }


        }

    }



    }
@Composable
fun NewsArticlesList(
    state: NewsScreanState,
    onCardClicked : (Article) -> Unit,
    onRetry : () -> Unit
){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
       // modifier = Modifier.padding(Padding)
    ){
        items(state.articals){ article ->
            NewsArticalCard(article = article, onCardClicked = onCardClicked,
                )
            // Text(text = article.title)


        }

    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (state.isLoading){
            CircularProgressIndicator()
        }
        if (state.error != null){
            RetryCount(error = state.error, onRetry = onRetry)
        }

    }

}

