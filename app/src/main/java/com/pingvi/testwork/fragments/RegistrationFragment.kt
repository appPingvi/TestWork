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
import kotlinx.android.synthetic.main.registration_layout.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegistrationFragment : Fragment(), View.OnClickListener {
    companion object {
        val TAG = "RegistrationFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.registration_layout,container,false)
    }

    override fun onStart() {
        super.onStart()
        button_next.setOnClickListener(this)
        button_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.button_next->
            {
                if(validate())
                {
                    val listener = activity as IFragmentButtonClick
                    listener.onFragmentButtonCLick(R.id.button_next)
                }
            }
            R.id.button_back->
            {
                if(activity!=null)
                    activity?.supportFragmentManager?.popBackStack()

            }
        }
    }
   private fun validEmail(): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val email_pattern = "^([a-zA-Z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$"
        pattern = Pattern.compile(email_pattern)
        matcher = pattern.matcher(userEmail.getText().toString())
        return matcher.matches()
    }
    private fun validate(): Boolean {
        var valid = true

        if (password_text.text.isEmpty() || password_text.text.length < 4) {
            valid = false
            password_text.error = "Слишком короткий пароль.Укажите пароль от 6 - 50 символов"
        } else password_text.error = null
        if (password_confirm.text.isEmpty() || password_text.text.equals(password_confirm.text)) {
            password_confirm.setError("Пароли не совпадают!")
            valid = false
        }

        if (name_text.text.isEmpty() || name_text.text.length < 4) {
            valid = false
            name_text.error = "Неверный формат!"
        } else name_text.error = null
        if (login_text.text.isEmpty()) {
            login_text.error = "Заполните это поле!"
            valid = false
        } else login_text.error = null
        if (userEmail.text.isEmpty() || !validEmail()) {
            valid = false
            userEmail.error = "Неверный формат!"
        } else userEmail.error = null

        return valid
    }
}