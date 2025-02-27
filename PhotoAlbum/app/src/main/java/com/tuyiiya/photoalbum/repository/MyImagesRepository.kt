package com.tuyiiya.photoalbum.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tuyiiya.photoalbum.model.MyImages
import com.tuyiiya.photoalbum.room.MyImagesDao
import com.tuyiiya.photoalbum.room.MyImagesDatabase

class MyImagesRepository(application: Application) {
    var myImagesDao: MyImagesDao
    var imagesList: LiveData<List<MyImages>>

    init {
        val database = MyImagesDatabase.getDatabaseInstance(application)
        myImagesDao = database.myImagesDao()
        imagesList = myImagesDao.getAllImages()
    }

    suspend fun insert(myImages: MyImages) {
        myImagesDao.insert(myImages)
    }

    suspend fun update(myImages: MyImages) {
        myImagesDao.update(myImages)
    }

    suspend fun delete(myImages: MyImages) {
        myImagesDao.delete(myImages)
    }

    fun getAllImages(): LiveData<List<MyImages>> {
        return imagesList
    }
}