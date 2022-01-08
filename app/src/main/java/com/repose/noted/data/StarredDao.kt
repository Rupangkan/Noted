package com.repose.noted.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StarredDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Starred)

    @Delete
    fun delete(item: Starred)

    @Query("SELECT * from starred WHERE dbname = :name")
    fun getItem(name: String): Flow<Starred>


    @Query("SELECT * from starred")
    fun getItems(): Flow<List<Starred>>


}