package com.pingvi.testwork.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pingvi.testwork.R
import com.pingvi.testwork.fragments.LoginFragment
import com.pingvi.testwork.fragments.RegistrationFragment
import com.pingvi.testwork.interfaces.IFragmentButtonClick
import com.pingvi.testwork.viewmodel.MainModelView
import kotlinx.android.synthetic.main.login_layout.*

class MainActivity : FragmentActivity(),IFragmentButtonClick {
    private lateinit var mViewModel: MainModelView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(this
        ).get(MainModelView::class.java)
        val loginFragment = LoginFragment()
        val transaction = supportFragmentManager.beginTransaction();
        if(supportFragmentManager.findFragmentByTag(LoginFragment.TAG) == null)
        {
            transaction.add(R.id.fragment_container,loginFragment,LoginFragment.TAG).addToBackStack(null);
            transaction.commit()
        }
        mViewModel.errorLogin.observe(this,Observer{
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onFragmentButtonCLick(buttonId: Int) {
      when(buttonId)
      {
            R.id.button_login ->
            {
                mViewModel.response.observe(this,Observer{
                    if(it!=null) {
                        val paymentActivity = Intent(this, PaymentsActivity::class.java)
                        paymentActivity.putExtra("token",it.token)
                        startActivity(paymentActivity)
                    }
                })
                mViewModel.getResultLogin(login_text.text.toString(),password_text.text.toString())
            }
            R.id.button_regist ->
            {
                val regFragment = RegistrationFragment()
                val transaction = supportFragmentManager.beginTransaction();
                if(supportFragmentManager.findFragmentByTag(RegistrationFragment.TAG) == null)
                    transaction.add(R.id.fragment_container,regFragment,RegistrationFragment.TAG)
                else
                    transaction.replace(R.id.fragment_container,regFragment,RegistrationFragment.TAG)
                transaction.addToBackStack(null)
                transaction.commit()
            }
      }
    }

    override fun onBackPressed() {

        if(supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed();
        }
        else if(supportFragmentManager.backStackEntryCount == 1) {
            moveTaskToBack(false);
        }
        else {
            supportFragmentManager.popBackStack();
        }

    }
}