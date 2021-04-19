package com.pingvi.testwork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PaymentVMFragment(val token:String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PaymentViewModel::class.java))
            return PaymentViewModel(token) as T
        throw IllegalArgumentException("Неизвестный класс")
    }
}