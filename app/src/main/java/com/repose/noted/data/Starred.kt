package com.repose.noted.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="starred")
data class Starred(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "path")
    val path: String,
    @ColumnInfo(name = "dbname")
    val dbname: String,
)