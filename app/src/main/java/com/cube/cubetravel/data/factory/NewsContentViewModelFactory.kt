package com.cube.cubetravel.data.factory

import android.app.Application
import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.data.repository.NewsContentRepository
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.viewmodel.NewsContentViewModel
import com.cube.cubetravel.manager.DatabaseManager

/** NewsContentViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class NewsContentViewModelFactory(private val intentNewsBean: NewsBean?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsContentViewModel::class.java)){

            val repository = NewsContentRepository(intentNewsBean)
            return NewsContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}