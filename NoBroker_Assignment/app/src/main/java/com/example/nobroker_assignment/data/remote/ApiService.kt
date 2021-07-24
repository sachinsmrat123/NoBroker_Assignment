package com.example.nobroker_assignment.data.remote


import com.example.nobroker_assignment.data.models.ResponseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL="https://api.jsonbin.io/"

interface ApiInterface{

    @GET("b/60fa8fefa917050205ce5470")
    suspend fun getPost() : ResponseModel

}
object ApiServices {
    val instance:ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(ApiInterface::class.java)

    }
}