package com.example.shaadicom.database

import androidx.room.*
import io.reactivex.Completable

@Dao
interface UserDetailsDao {
    @Update
    fun updateUser(userEntity: UserDetailsEntity)

    @Insert
    fun insertAll(users: List<UserDetailsEntity>)

    @Query("SELECT * FROM  USER_DETAILS_TBL")
    fun getUsers(): List<UserDetailsEntity>
}