package com.frezzcoding.jpm.data.api

import com.frezzcoding.jpm.data.models.Album
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    fun getAlbums() : Single<List<Album>>

}