package com.cube.cubetravel.data.factory

import android.app.Application
import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.manager.DatabaseManager

/** MainViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            val attractionsCollectionDao = DatabaseManager.getDatabase(application).attractionsCollectionDao()

            val repository = MainRepository(attractionsCollectionDao)
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}