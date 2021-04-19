package com.pingvi.testwork.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pingvi.testwork.R
import com.pingvi.testwork.model.ResponsePayments
import kotlinx.android.synthetic.main.item_layout.view.*

class PaymentAdapter(private val payments: ArrayList<ResponsePayments>) : RecyclerView.Adapter<PaymentAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(payment: ResponsePayments) {
            itemView.apply {
                tvDescription.text = payment.desc
                tvAmout.text = payment.amount.toString()
                tvCurrency.text = payment.currency
                tvCreated.text = payment.created.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = payments.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(payments[position])
    }

    fun addPayments(payments: List<ResponsePayments>) {
        this.payments.apply {
            clear()
            addAll(payments)
        }

    }
}