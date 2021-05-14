package com.frezzcoding.jpm.functionalities.listalbum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.frezzcoding.jpm.R
import com.frezzcoding.jpm.data.models.AlbumDto

class AlbumListAdapter(private val listener : AlbumClickListener) : ListAdapter<AlbumDto, AlbumViewHolder>(AlbumsDiffUtil()) {


    class AlbumsDiffUtil : DiffUtil.ItemCallback<AlbumDto>(){
        override fun areItemsTheSame(oldItem: AlbumDto, newItem: AlbumDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumDto, newItem: AlbumDto): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        getItem(position)?.let { album ->
            holder.bindTo(album)
            holder.itemView.setOnClickListener {
                listener.onAlbumClick(album)
            }
        }
    }

    interface AlbumClickListener{
        fun onAlbumClick(album : AlbumDto)
    }

}