package com.example.yougovchallenge.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.yougovchallenge.base.BaseViewModel
import kotlinx.coroutines.launch

class TimersViewModel : BaseViewModel() {

    private val _timerData: MutableLiveData<Boolean> = MutableLiveData()
    val timerData get() = _timerData

    fun initChangeListener() {
        viewModelScope.launch {
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        _timerData.postValue(true)
    }

}