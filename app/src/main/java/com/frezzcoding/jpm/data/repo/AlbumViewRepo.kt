package com.frezzcoding.jpm.data.repo

import com.frezzcoding.jpm.data.models.AlbumDto
import io.reactivex.Completable
import io.reactivex.Single

interface AlbumViewRepo {
    fun getAlbums() : Single<List<AlbumDto>>
    fun cacheAlbums(list : List<AlbumDto>) : Completable
    fun getCachedAlbums() : Single<List<AlbumDto>>
    fun getAlbumData(id : Int) : Single<AlbumDto>
}