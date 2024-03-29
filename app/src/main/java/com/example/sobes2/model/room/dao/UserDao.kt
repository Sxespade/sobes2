package ru.geekbrains.poplib.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.geekbrains.poplib.mvp.model.entity.room.RoomUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(vararg users: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(vararg users: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    @Query("SELECT * FROM RoomUser")
    fun getAll(): List<RoomUser>

    @Query("SELECT * FROM RoomUser WHERE firstName = :login LIMIT 1")
    fun findByLogin(login: String): RoomUser?
}