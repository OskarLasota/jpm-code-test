package com.frezzcoding.jpm


import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.frezzcoding.jpm.data.models.AlbumDto
import com.frezzcoding.jpm.data.repo.AlbumViewRepo
import com.frezzcoding.jpm.functionalities.listalbum.AlbumViewModel
import com.frezzcoding.jpm.test_utils.TestAppScheduler
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class AlbumViewModelTest {
    private val scheduler = TestAppScheduler()

    @Fixture
    lateinit var fixtAlbumDTO: List<AlbumDto>
    @Mock
    lateinit var mockCompositeDisposable: CompositeDisposable
    @Mock
    lateinit var mockAlbumRepo: AlbumViewRepo
    lateinit var sut: AlbumViewModel

    @Before
    fun setUp() {
        FixtureAnnotations.initFixtures(this)
        MockitoAnnotations.initMocks(this)
        sut = AlbumViewModel(mockAlbumRepo, mockCompositeDisposable, scheduler)
    }

    @Test
    fun successfully_getAlbums_and_cache_data_ifNotEmpty() {
        //when
        whenever(mockAlbumRepo.getAlbums()).thenReturn(Single.just(fixtAlbumDTO))

        //run
        sut.getAlbums()

        //verify
        verify(mockAlbumRepo).getAlbums()
        verify(mockAlbumRepo).cacheAlbums(fixtAlbumDTO)
    }

    @Test
    fun successfully_get_cachedAlbums() {
        //when
        whenever(mockAlbumRepo.getCachedAlbums()).thenReturn(Single.just(fixtAlbumDTO))

        //run
        sut.getCachedAlbums()

        //verify
        verify(mockAlbumRepo).getCachedAlbums()
    }

    @Test
    fun successfully_get_albums_if_cache_is_empty() {
        //when
        whenever(mockAlbumRepo.getCachedAlbums()).thenReturn(Single.just(emptyList()))

        //run
        sut.getCachedAlbums()

        //verify
        verify(mockAlbumRepo).getAlbums()
    }

}