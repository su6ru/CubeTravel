package com.cube.cubetravel.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.repository.WebRepository
import com.cube.cubetravel.feature.web.viewmodel.WebViewModel

/** WebViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class WebViewModelFactory(private val intentWebBean: WebBean?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebViewModel::class.java)){

            val repository = WebRepository(intentWebBean)
            return WebViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
