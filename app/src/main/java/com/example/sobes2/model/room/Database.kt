package com.example.sobes2.model.room

import androidx.room.RoomDatabase
import com.example.sobes2.model.room.room.RoomCachedImage
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.RoomUser
import ru.geekbrains.poplib.mvp.model.entity.room.dao.ImageDao
import ru.geekbrains.poplib.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.poplib.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomUser::class, RoomGithubRepository::class, RoomCachedImage::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}