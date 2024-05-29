package com.cube.cubetravel.feature.main.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.cube.cubetravel.data.beans.AttractionsCollectionBean

/** 景點收藏列表專用的 DiffCallback */
class AttractionsCollectionListDiffCallback : DiffUtil.ItemCallback<AttractionsCollectionBean>() {
    override fun areItemsTheSame(oldItem: AttractionsCollectionBean, newItem: AttractionsCollectionBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AttractionsCollectionBean, newItem: AttractionsCollectionBean): Boolean {
        return oldItem == newItem
    }
}