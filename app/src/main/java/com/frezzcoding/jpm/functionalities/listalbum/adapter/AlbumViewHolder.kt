package com.frezzcoding.jpm.functionalities.listalbum.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.jpm.data.models.AlbumDto
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(album: AlbumDto) {
        itemView.tv_album_title.text = album.title
    }

}