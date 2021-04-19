package com.pingvi.testwork.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.pingvi.testwork.R
import com.pingvi.testwork.interfaces.IFragmentButtonClick
import kotlinx.android.synthetic.main.login_layout.*

class LoginFragment : Fragment(), View.OnClickListener {

    companion object {
        val TAG = "LoginFragment"
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.login_layout, container, false)
    }

    override fun onStart() {
        super.onStart()
        button_login.setOnClickListener(this)
        button_regist.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(validation()) {
            val listener = activity as IFragmentButtonClick
            if (v != null)
                listener.onFragmentButtonCLick(v.id)
        }
    }

    fun validation():Boolean{
        var valid = true

        if (password_text.text.isEmpty()) {
            valid = false
            password_text.error = "Неверно указан пароль"
        } else password_text.error = null
        if (login_text.text.isEmpty()) {
            login_text.setError("Укажите логин")
            valid = false
        }
        return valid
    }
}