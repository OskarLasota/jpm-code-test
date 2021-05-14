package com.frezzcoding.jpm.functionalities.listalbum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.jpm.data.models.AlbumDto
import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun getAlbums() {
        compositeDisposable.add(
            repo.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { list -> list.sortedBy { it.title } }
                .doOnSubscribe { _loading.postValue(true) }
                .subscribe({ list ->
                    _albums.postValue(list)
                    cacheAlbums(list)
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                })
        )
    }

    fun getCachedAlbums() {
        compositeDisposable.add(
            repo.getCachedAlbums()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _loading.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    if (list.isEmpty()) {
                        getAlbums()
                    } else {
                        _albums.postValue(list)
                    }
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                })
        )
    }

    private fun cacheAlbums(list: List<AlbumDto>) {
        compositeDisposable.add(
            repo.cacheAlbums(list)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _loading.postValue(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                })
        )
    }


}