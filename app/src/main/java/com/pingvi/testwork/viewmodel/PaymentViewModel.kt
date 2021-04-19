package com.pingvi.testwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pingvi.testwork.api.RetrofitBuilder
import com.pingvi.testwork.model.ResponsePayments
import com.pingvi.testwork.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PaymentViewModel(val token:String): ViewModel(){

    val paymentsList = MutableLiveData<List<ResponsePayments>>()
    fun getResultPayments()
    {
        GlobalScope.launch(Dispatchers.IO) {
            val getdata = RetrofitBuilder.apiService.getPaymentsList("123456789")
            if(getdata.isSuccessful)
            {
                val body = getdata.body()
                if(body!=null)
                {
                    paymentsList.postValue(body.response)
                }
            }
        }
    }
}