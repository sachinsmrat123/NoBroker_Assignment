package com.example.nobroker_assignment.ui.adapter

import android.app.Application
import com.example.nobroker_assignment.data.local.MyDatabase
import com.example.nobroker_assignment.repository.MyRepository


class ApplicationClass:Application() {
    val myDao by lazy {
        val roomDatabase= MyDatabase.getDatabase(this)
        roomDatabase.getMyDao()
    }
    val repository:MyRepository by lazy {
        MyRepository(myDao)
    }

}