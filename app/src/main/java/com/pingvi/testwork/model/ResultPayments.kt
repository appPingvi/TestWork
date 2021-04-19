package com.pingvi.testwork.model

import com.google.gson.annotations.SerializedName

data class ResultPayments (
    val success:String,
    val response:List<ResponsePayments>
)

data class ResponsePayments(
    val desc:String,
    val amount:Double,
    val currency: String,
    val created: Int
)
