package com.frezzcoding.jpm.functionalities.listalbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.jpm.data.models.AlbumDto
import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repo: AlbumViewRepo,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _albums = MutableLiveData<List<AlbumDto>>()
    val albums: LiveData<List<AlbumDto>> = _albums

    private fun getAlbums() {
        compositeDisposable.add(
            repo.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    _albums.postValue(list)
                    cacheAlbums(list)
                }, {
                    //error
                })
        )
    }

    fun getCachedAlbums(){
        compositeDisposable.add(
            repo.getCachedAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    if(list.isEmpty()){
                        getAlbums()
                    }else {
                        _albums.postValue(list)
                    }
                },{
                    //error
                })
        )
    }

    private fun cacheAlbums(list : List<AlbumDto>){
        compositeDisposable.add(
            repo.cacheAlbums(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //success
                },{
                    //error
                })
        )
    }





}