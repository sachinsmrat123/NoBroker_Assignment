package com.example.nobroker_assignment.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(),ItemClickListener,SearchView.OnQueryTextListener {
    private lateinit var viewModel: MyViewModel
    private lateinit var adapter2:PostAdapter
    private lateinit var shimmerEffect:ShimmerFrameLayout
    val entity= mutableListOf<MyEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        /**
         * here we initializing our shimmerEffect UI
         */
        shimmerEffect=findViewById(R.id.shimmer_view_container)
        adapter2= PostAdapter(entity,this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter2
        val app=application as ApplicationClass
        val viewModelFactory= MyViewModelFactory(app.repository)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(MyViewModel::class.java)
        viewModel.getPosts().observe(this, Observer {
            /**
             * for some delay we are setting our adapter with help of handler
             */
        Handler(Looper.getMainLooper()).postDelayed({

                entity.clear()
                entity.addAll(it)
                shimmerEffect.stopShimmer()
                shimmerEffect.visibility= View.GONE
                adapter2.notifyDataSetChanged()
            },3000)
            })


        CoroutineScope(Dispatchers.IO).launch {
            if(viewModel.getCount()==0) {
                viewModel.insertPosts()
            }
        }

    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.getSearchData(searchQuery).observe(this, { list ->
            list.let {
                adapter2.setData(it)
            }
        })
    }

    /**
     *passing the data to description activity
     */
    override fun onItemClicked(myEntity: MyEntity) {
        val stream = ByteArrayOutputStream()
        myEntity.image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val image = stream.toByteArray()
            val intent=Intent(this,Description::class.java)
        intent.putExtra("title",myEntity.title)
        intent.putExtra("subTitle",myEntity.subTitle)
        intent.putExtra("image",image)
        startActivity(intent)


    }

    /**
     * this method is responsible for searchView implementation
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }
    override fun onQueryTextSubmit(query: String?): Boolean {

        return  true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchDatabase(newText)
        }
        return true
    }
}