package com.example.nobroker_assignment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nobroker_assignment.R
import com.example.nobroker_assignment.data.local.MyEntity
import com.example.nobroker_assignment.ui.adapter.ApplicationClass
import com.example.nobroker_assignment.ui.adapter.PostAdapter
import com.example.nobroker_assignment.ui.listeners.ItemClickListener
import com.example.nobroker_assignment.viewmodel.MyViewModel
import com.example.nobroker_assignment.viewmodel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),ItemClickListener {
    private lateinit var viewModel: MyViewModel
    val entity= mutableListOf<MyEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter2= PostAdapter(entity,this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter2
        val app=application as ApplicationClass
        val viewModelFactory= MyViewModelFactory(app.repository)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(MyViewModel::class.java)
        viewModel.getPosts().observe(this, Observer {
            entity.clear()
            entity.addAll(it)
            adapter2.notifyDataSetChanged()

        })
        CoroutineScope(Dispatchers.IO).launch {
            if(viewModel.getCount()==0) {
                viewModel.insertPosts()
            }
        }
    }

    override fun onItemClicked(myEntity: MyEntity) {
        TODO("Not yet implemented")
    }
}