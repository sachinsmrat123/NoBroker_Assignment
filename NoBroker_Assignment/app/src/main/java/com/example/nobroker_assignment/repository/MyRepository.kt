package com.example.nobroker_assignment.repository

import androidx.lifecycle.LiveData
import com.example.nobroker_assignment.data.local.MyDao
import com.example.nobroker_assignment.data.local.MyEntity
import com.example.nobroker_assignment.data.remote.ApiServices

class MyRepository(val myDao: MyDao) {
    val api= ApiServices.instance
    suspend fun insertPosts(){
        val result=api.getPost()
        for (i in 0 until result.size) {
            val myEntity = MyEntity(result[i].image, result[i].title, result[i].subTitle)
            myDao.insertPosts(myEntity)
        }

    }
    fun getPosts(): LiveData<List<MyEntity>> {
        return myDao.getPosts()
    }
}