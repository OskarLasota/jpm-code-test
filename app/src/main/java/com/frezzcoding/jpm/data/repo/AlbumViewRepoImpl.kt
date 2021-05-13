package com.frezzcoding.jpm.data.repo

import com.frezzcoding.jpm.data.api.ApiService
import com.frezzcoding.jpm.data.models.AlbumDto
import io.reactivex.Single
import javax.inject.Inject

class AlbumViewRepoImpl @Inject constructor(private val api : ApiService): AlbumViewRepo {

    override fun getAlbums(): Single<List<AlbumDto>> {
        return Single.defer {
            return@defer api.getAlbums()
        }
    }
}