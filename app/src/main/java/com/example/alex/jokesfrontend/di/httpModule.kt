package com.example.alex.jokesfrontend.di

import com.example.alex.jokesfrontend.BuildConfig
import com.example.alex.jokesfrontend.NewsViewModel
import com.example.alex.jokesfrontend.domain.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val httpModule : Module = applicationContext {
    bean { createOkHttpClient()}
    bean { createWebService<NewsService>(get(), BuildConfig.SERVER_URL) }
    viewModel { NewsViewModel(get(), get()) }
}

fun createOkHttpClient(): OkHttpClient {
    Timber.d("new http client")
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    Timber.d("new retrofit")
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}