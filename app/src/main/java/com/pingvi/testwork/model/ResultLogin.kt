package com.pingvi.testwork.model

data class ResultLogin(
    val success:String,
    val response:ResponseLogin,
    val error:ErrorResponse
)
data class ResponseLogin (val token:String)
data class ErrorResponse(val error_code:Int,val error_msg:String = "")
