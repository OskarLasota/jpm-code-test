package com.frezzcoding.jpm.functionalities.listalbum

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.jpm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment(R.layout.fragment_albumlist) {

    private val viewModel by viewModels<AlbumViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getAlbums()
    }

    private fun setupObservers(){

    }

}