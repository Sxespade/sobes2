package ru.geekbrains.poplib.mvp.model.entity.room

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomUser(
    @PrimaryKey
    var id: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var img: String
)
