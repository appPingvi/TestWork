package com.pingvi.testwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pingvi.testwork.api.RetrofitBuilder
import com.pingvi.testwork.model.ResponseLogin
import com.pingvi.testwork.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainModelView():ViewModel() {

    val response = MutableLiveData<ResponseLogin>()
    val errorLogin = MutableLiveData<String>()
    fun getResultLogin(login:String, pass:String)
    {
        GlobalScope.launch(Dispatchers.IO) {
            val getdata = RetrofitBuilder.apiService.getResultLogin(User(login,pass))
            if(getdata.isSuccessful)
            {
                val body = getdata.body()
                if(body!=null)
                {
                    errorLogin.postValue(body.error?.error_msg)
                    if(body.success == "true")
                        response.postValue(body.response)
                }
            }
        }
    }

}