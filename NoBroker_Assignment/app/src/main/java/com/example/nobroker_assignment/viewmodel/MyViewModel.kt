package com.example.nobroker_assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nobroker_assignment.data.local.MyEntity
import com.example.nobroker_assignment.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: MyRepository
) : ViewModel() {

    fun insertPosts(){

        CoroutineScope(Dispatchers.IO).launch {
            repository.insertPosts()
        }
    }
    fun getPosts(): LiveData<List<MyEntity>> {
        return repository.getPosts()
    }

    fun getCount():Int{
        return repository.getCount()
    }

    fun getSearchData(query:String):LiveData<List<MyEntity>>{
        return repository.getSearchData(query).asLiveData()
    }
}