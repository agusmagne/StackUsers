package com.example.stackusers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    companion object {
        val ErrorNotification = MutableLiveData<BaseViewModel.Event<Int>>()
        val SuccessNotification = MutableLiveData<BaseViewModel.Event<Int>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ErrorNotification.observeEvent(this) {
            ErrorDialogFragment.newInstance(it).show(supportFragmentManager, null)
        }

        SuccessNotification.observeEvent(this) {
            Snackbar.make(findViewById(android.R.id.content), getString(it), Snackbar.LENGTH_LONG)
                .show()
        }
    }

}