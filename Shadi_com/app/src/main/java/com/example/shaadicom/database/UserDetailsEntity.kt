package com.example.shaadicom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_DETAILS_TBL")
data class UserDetailsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "USERID")
    val userid: Int,
    @ColumnInfo(name = "FULLNAME")
    var fullname: String,
    @ColumnInfo(name = "AGEPLUSGENDER")
    var ageplusgender:String,
    @ColumnInfo(name = "LOCATION")
    var location: String,
    @ColumnInfo(name = "IMAGEURL")
    var imageurl: String,
    @ColumnInfo(name = "ACCEPT")
    var accept: String="Accept",
    @ColumnInfo(name = "DECLINE")
    var decline:String="Decline"
    )