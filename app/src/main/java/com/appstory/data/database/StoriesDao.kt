package com.appstory.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appstory.data.network.respons.ListStoryItem

@Dao
interface StoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(story: List<ListStoryItem>)

    @Query("SELECT * FROM stories")
    fun getAllStory(): PagingSource<Int, ListStoryItem>

    @Query("DELETE FROM stories")
    suspend fun deleteAll()
}