package com.example.nobroker_assignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(myEntity: MyEntity)

    @Query("select * from my_nobroker_table")
    fun getPosts(): LiveData<List<MyEntity>>

    @Query("select count(*) from my_nobroker_table")
    fun countCol():Int

    @Query("SELECT * FROM my_nobroker_table WHERE title LIKE :searchQuery OR subTitle LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<MyEntity>>
}