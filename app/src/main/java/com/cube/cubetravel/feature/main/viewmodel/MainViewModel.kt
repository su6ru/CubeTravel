package com.cube.cubetravel.feature.main.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.repository.MainRepository
/** MainActivity相關的  ViewModel*/
class MainViewModel(private val mainRepository: MainRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    // MARK:- ========================== Data

    // MARK:- ========================== Event

    // MARK:- ========================== Method

}