package com.frezzcoding.jpm.functionalities.listalbum

import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repo: AlbumViewRepo,
    val compositeDisposable: CompositeDisposable
) {


    fun getAlbums() {
        compositeDisposable.add(
            repo.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                })
        )
    }


}