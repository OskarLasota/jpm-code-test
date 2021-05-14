package com.frezzcoding.jpm.functionalities.listalbum

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.frezzcoding.jpm.R
import com.frezzcoding.jpm.data.models.AlbumDto
import com.frezzcoding.jpm.functionalities.listalbum.adapter.AlbumListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_albumlist.*

@AndroidEntryPoint
class AlbumListFragment : Fragment(R.layout.fragment_albumlist), AlbumListAdapter.AlbumClickListener {

    private val viewModel by viewModels<AlbumViewModel>()
    private lateinit var albumListAdapter : AlbumListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        viewModel.getCachedAlbums()
    }

    private fun setupObservers(){
        viewModel.albums.observe(viewLifecycleOwner, {
            albumListAdapter.submitList(it)
        })
    }

    private fun setupAdapter(){
        albumListAdapter = AlbumListAdapter(this)
        recycler_album_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = albumListAdapter
        }
    }

    override fun onAlbumClick(album : AlbumDto) {
        //navigate
    }

}