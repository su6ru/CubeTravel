package com.cube.cubetravel.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.repository.AttractionsCollectionContentRepository
import com.cube.cubetravel.data.repository.AttractionsContentRepository
import com.cube.cubetravel.feature.attractions.viewmodel.AttractionsContentViewModel
import com.cube.cubetravel.feature.attractions_collection.viewmodel.AttractionsCollectionContentViewModel

/** AttractionsContentViewModel  專用的 Factory */

@Suppress("UNCHECKED_CAST")
class AttractionsCollectionContentViewModelFactory(private val intentAttractionsCollectionBean: AttractionsCollectionBean?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttractionsCollectionContentViewModel::class.java)){

            val repository = AttractionsCollectionContentRepository(intentAttractionsCollectionBean)
            return AttractionsCollectionContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}