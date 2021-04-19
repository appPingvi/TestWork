package com.pingvi.testwork.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pingvi.testwork.R
import com.pingvi.testwork.adapters.PaymentAdapter
import com.pingvi.testwork.model.ResponsePayments
import com.pingvi.testwork.viewmodel.PaymentVMFragment
import com.pingvi.testwork.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.payment_layout.*

class PaymentsActivity: AppCompatActivity() {
    private lateinit var vmPayment: PaymentViewModel
    private lateinit var adapter:PaymentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_layout)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        btn_logout.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PaymentAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        val token = intent.getStringExtra("token")
        vmPayment = ViewModelProvider(this,PaymentVMFragment(token.toString())).get(PaymentViewModel::class.java)
        vmPayment.paymentsList.observe(this, Observer{
            if(it!=null)
                retrieveList(it)
            })

        vmPayment.getResultPayments()
    }
    private fun retrieveList(payments: List<ResponsePayments>) {
        adapter.apply {
            addPayments(payments)
            notifyDataSetChanged()
        }
    }
}