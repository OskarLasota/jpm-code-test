package com.frezzcoding.jpm.functionalities.albumview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.jpm.common.scheduler.AppScheduler
import com.frezzcoding.jpm.data.models.AlbumDto
import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repo: AlbumViewRepo,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler: AppScheduler
) : ViewModel() {

    private val _album = MutableLiveData<AlbumDto>()
    val album: LiveData<AlbumDto> = _album

    fun getAlbumData(id : Int) {
        compositeDisposable.add(
            repo.getAlbumData(id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({
                    _album.postValue(it)
                    println("album data $it")
                },{
                    println("error : $it")
                })
        )
    }

}