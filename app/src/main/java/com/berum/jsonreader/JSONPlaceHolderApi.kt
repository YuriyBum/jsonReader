package com.berum.jsonreader

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface JSONPlaceHolderApi {
    @get:GET("test.php")
    val post: Call<ArrayList<post>>

    @GET("test.php?")
    fun getCompany(@Query("id") id: String): Call<ArrayList<SingleCompany>>
}