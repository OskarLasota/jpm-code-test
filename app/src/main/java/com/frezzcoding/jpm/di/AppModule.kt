package com.frezzcoding.jpm.di

import android.content.Context
import androidx.room.Room
import com.frezzcoding.jpm.data.api.ApiClient
import com.frezzcoding.jpm.data.api.ApiService
import com.frezzcoding.jpm.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext : Context) : AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideApiService() : ApiService?{
        return ApiClient.build()
    }

    @Provides
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }

}