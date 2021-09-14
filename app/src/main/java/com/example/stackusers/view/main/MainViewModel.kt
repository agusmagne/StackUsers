package com.example.stackusers.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stackusers.R
import com.example.stackusers.model.entities.StackUser
import com.example.stackusers.model.service.StackService
import com.example.stackusers.view.BaseActivity
import com.example.stackusers.view.BaseViewModel
import com.example.stackusers.view.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stackService: StackService
) : BaseViewModel() {

    private val _userListLD = MutableLiveData<List<StackUser>>()
    val userListLD = _userListLD as LiveData<List<StackUser>>

    fun getUsers(filter: String = "") {
        safeCall {
            _userListLD.postValue(stackService.getUsers(filter))
            BaseActivity.SuccessNotification.postEvent(R.string.get_users_success)
        }
    }

}