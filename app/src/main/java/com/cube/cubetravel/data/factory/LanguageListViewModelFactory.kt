package com.cube.cubetravel.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.repository.LanguageListRepository
import com.cube.cubetravel.feature.language.viewmodel.LanguageListViewModel
import com.cube.cubetravel.feature.news.viewmodel.NewsContentViewModel

/** LanguageListViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class LanguageListViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageListViewModel::class.java)){

            val repository = LanguageListRepository(context)

            return LanguageListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}