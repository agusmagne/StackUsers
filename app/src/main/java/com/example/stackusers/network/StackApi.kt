package com.example.stackusers.network

import com.example.stackusers.model.responses.GetUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackApi {

    @GET("/2.3/users?pagesize=20&order=asc&sort=name&site=stackoverflow")
    suspend fun getUsers(@Query("inname") filter: String): Response<GetUsersResponse>

}