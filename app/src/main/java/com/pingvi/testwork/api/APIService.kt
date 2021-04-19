package com.pingvi.testwork.api

import com.pingvi.testwork.model.ResultPayments
import com.pingvi.testwork.model.ResultLogin
import com.pingvi.testwork.model.User
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @Headers("app-key:12345","v:1")
    @POST("login")
    suspend fun getResultLogin(@Body user: User):Response<ResultLogin>
    @Headers("app-key:12345","v:1")
    @GET("payments")
    suspend fun getPaymentsList(@Query("token")token:String): Response<ResultPayments>
}