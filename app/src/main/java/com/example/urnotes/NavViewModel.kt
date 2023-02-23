package com.example.urnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavViewModel: ViewModel() {

    private var _id = MutableLiveData<Int?>(null)
    val id: LiveData<Int?> = _id
    fun setCurrentId(id: Int?){
        _id.value = id
    }

}