package com.frezzcoding.jpm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frezzcoding.jpm.data.models.AlbumDto
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list : List<AlbumDto>) : Completable

    @Query("SELECT * FROM album_table")
    fun getAlbumList() : Single<List<AlbumDto>>

}