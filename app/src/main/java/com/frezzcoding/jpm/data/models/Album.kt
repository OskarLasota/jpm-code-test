package com.frezzcoding.jpm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Album(@PrimaryKey(autoGenerate = true) val userId : Int, val id : Int, val title : String)