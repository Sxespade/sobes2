package com.example.sobes2.model.room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class RoomCachedImage(
    @PrimaryKey val url: String,
    val localPath: String
)