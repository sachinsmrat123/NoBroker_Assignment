package com.example.nobroker_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nobroker_assignment.repository.MyRepository

/**
 * for following proper MVVM architecture created this class
 */
class MyViewModelFactory(private val myRepository: MyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(myRepository) as T
    }
}