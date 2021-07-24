package com.example.nobroker_assignment.data.remote


import com.example.nobroker_assignment.data.models.ResponseModel
import retrofit2.http.*


interface APIService {


    @GET("/60fa8fefa917050205ce5470")
    suspend fun guessMyAge(
        @Header("Content-Type") contentType: String,
        @Query("name") name: String
    ): ResponseModel

}