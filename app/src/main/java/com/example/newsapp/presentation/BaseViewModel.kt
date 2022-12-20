package com.example.newsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.Result
import com.example.newsapp.domain.entities.NewsDomain

open class BaseViewModel : ViewModel() {

    protected val _state = MutableLiveData<Result<NewsDomain>>()
    val state: LiveData<Result<NewsDomain>>
        get() = _state

}