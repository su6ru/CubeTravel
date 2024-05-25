package com.cube.cubetravel.data.factory

import android.app.Application
import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.repository.AttractionsContentRepository
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.data.repository.NewsContentRepository
import com.cube.cubetravel.feature.attractions.viewmodel.AttractionsContentViewModel
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.viewmodel.NewsContentViewModel
import com.cube.cubetravel.manager.DatabaseManager

/** AttractionsContentViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class AttractionsContentViewModelFactory(private val intentAttractionsBean: AttractionsBean?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionsContentViewModel::class.java)){

            val repository = AttractionsContentRepository(intentAttractionsBean)
            return AttractionsContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}