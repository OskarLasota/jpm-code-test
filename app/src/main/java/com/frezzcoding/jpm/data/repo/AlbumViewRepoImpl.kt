package com.frezzcoding.jpm.data.repo

import com.frezzcoding.jpm.data.api.ApiService
import com.frezzcoding.jpm.data.models.Album
import io.reactivex.Single
import javax.inject.Inject

class AlbumViewRepoImpl @Inject constructor(private val api : ApiService): AlbumViewRepo {

    override fun getAlbums(): Single<List<Album>> {
        return Single.defer {
            return@defer api.getAlbums()
        }
    }

}