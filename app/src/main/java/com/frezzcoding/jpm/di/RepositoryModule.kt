package com.frezzcoding.jpm.di

import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import com.frezzcoding.jpm.data.repo.AlbumViewRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlbumViewRepo(repo: AlbumViewRepoImpl): AlbumViewRepo

}