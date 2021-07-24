package com.example.nobroker_assignment.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nobroker_assignment.R
import com.example.nobroker_assignment.data.local.MyEntity
import com.example.nobroker_assignment.ui.listeners.ItemClickListener
import kotlinx.android.synthetic.main.item_layout.view.*

class PostAdapter( val postList:List<MyEntity>, val listner: ItemClickListener):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.apply {
            image.setImageBitmap(postList[position].image)
            tvTitle.text= postList[position].title
            tvSubTitle.text=postList[position].subTitle
        }
        holder.itemView.setOnClickListener {
            listner.onItemClicked(postList[position])
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}