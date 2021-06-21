package com.frezzcoding.jpm.functionalities.albumview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.jpm.R
import com.frezzcoding.jpm.databinding.FragmentAlbumviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumViewFragment : Fragment() {

    private val viewModel by viewModels<AlbumViewModel>()
    private lateinit var binding : FragmentAlbumviewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = arguments?.getInt("id") ?: throw IllegalArgumentException("Missing album id")
        viewModel.getAlbumData(id)
    }

}