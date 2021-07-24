package com.example.nobroker_assignment.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import com.example.nobroker_assignment.data.local.MyDao
import com.example.nobroker_assignment.data.local.MyEntity
import com.example.nobroker_assignment.data.remote.ApiServices
import kotlinx.coroutines.flow.Flow
import java.net.URL

class MyRepository(private val myDao: MyDao) {
    val api= ApiServices.instance
    suspend fun insertPosts(){
        val result=api.getPost()
        for (i in 0 until result.size) {
            val myEntity = MyEntity(urlToBitmap(result[i].image), result[i].title, result[i].subTitle)
            myDao.insertPosts(myEntity)
        }

    }
    fun getPosts(): LiveData<List<MyEntity>> {
        return myDao.getPosts()
    }

    private fun urlToBitmap(url:String):Bitmap{
        val uri=URL(url)
        val imageBitMap=BitmapFactory.decodeStream(uri.openConnection().getInputStream())
        return imageBitMap
    }

    fun getCount():Int{
        return myDao.countCol()
    }

    fun getSearchData(query:String): Flow<List<MyEntity>> {
        return myDao.searchDatabase(query)
    }
}