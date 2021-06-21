package com.frezzcoding.jpm.data.api

import com.frezzcoding.jpm.data.models.AlbumDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("albums")
    fun getAlbums() : Single<List<AlbumDto>>

    @GET("albums/{id}")
    fun getAlbumById(@Path("id") id : Int) : Single<AlbumDto>

}