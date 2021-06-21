package com.frezzcoding.jpm.data.repo

import com.frezzcoding.jpm.data.api.ApiService
import com.frezzcoding.jpm.data.database.AlbumDao
import com.frezzcoding.jpm.data.models.AlbumDto
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AlbumViewRepoImpl @Inject constructor(private val api : ApiService, private val albumDao : AlbumDao): AlbumViewRepo {

    override fun getAlbums(): Single<List<AlbumDto>> {
        return Single.defer {
            return@defer api.getAlbums()
        }
    }

    override fun cacheAlbums(list : List<AlbumDto>): Completable {
        return albumDao.insertList(list)
    }

    override fun getCachedAlbums(): Single<List<AlbumDto>> {
        return Single.defer{
            return@defer albumDao.getAlbumList()
        }
    }

    override fun getAlbumData(id : Int): Single<AlbumDto> {
        return Single.defer{
            return@defer api.getAlbumById(id)
        }
    }


}