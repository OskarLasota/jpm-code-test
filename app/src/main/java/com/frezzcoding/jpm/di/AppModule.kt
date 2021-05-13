package com.frezzcoding.jpm.di

import android.content.Context
import androidx.room.Room
import com.frezzcoding.jpm.common.UrlProvider
import com.frezzcoding.jpm.data.api.ApiService
import com.frezzcoding.jpm.data.database.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =retrofit.create(ApiService::class.java)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideRetrofit(
        urlProvider: UrlProvider,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.getApiURL())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideUrlProvider() = UrlProvider()


}