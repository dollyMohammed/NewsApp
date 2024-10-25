package com.example.questionapp.presentabon.news_screan.di

import androidx.compose.ui.tooling.preview.Preview
import com.example.questionapp.Repestory.NewsRepestory
import com.example.questionapp.data.remote.NewsApp
import com.example.questionapp.data.remote.NewsApp.Companion.BASE_URL
import com.example.questionapp.data.repestory.NewsRepestoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi():NewsApp{
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApp::class.java)

    }
    @Provides
    @Singleton
    fun providesNewsRepostoryitary(newsApp: NewsApp): NewsRepestory {
        return NewsRepestoryImpl(newsApp)

    }

}