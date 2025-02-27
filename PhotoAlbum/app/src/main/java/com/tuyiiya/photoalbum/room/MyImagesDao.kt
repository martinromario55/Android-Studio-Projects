package com.tuyiiya.photoalbum.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tuyiiya.photoalbum.model.MyImages

@Dao
interface MyImagesDao {
    @Insert
    suspend fun insert(myImages: MyImages)

    @Update
    suspend fun update(myImages: MyImages)

    @Delete
    suspend fun delete(myImages: MyImages)

    @Query("SELECT * FROM my_images ORDER BY imageId ASC")
    fun getAllImages(): LiveData<List<MyImages>>
}