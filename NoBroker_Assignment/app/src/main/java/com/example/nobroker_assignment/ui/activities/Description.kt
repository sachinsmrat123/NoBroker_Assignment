package com.example.nobroker_assignment.ui.activities

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nobroker_assignment.R
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.item_layout.*

class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        if(intent.extras!=null){
           val title=intent.getStringExtra("title")
            val subTilte=intent.getStringExtra("subTitle")
            val image=intent.getByteArrayExtra("image")
            val bitMap= BitmapFactory.decodeByteArray(image, 0, image!!.size)
            tvTitleDesc.setText(title.toString())
            tvSubTitleDesc.setText(subTilte.toString())
            ivImageDesc.setImageBitmap(bitMap)
        }


    }


    override fun onBackPressed() {

        super.onBackPressed()
    }
}