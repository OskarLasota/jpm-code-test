package com.frezzcoding.jpm.data.repo

import com.frezzcoding.jpm.data.models.Album
import io.reactivex.Single

interface AlbumViewRepo {
    fun getAlbums() : Single<List<Album>>
}