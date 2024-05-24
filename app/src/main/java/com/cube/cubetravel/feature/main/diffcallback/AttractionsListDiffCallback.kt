package com.cube.cubetravel.feature.main.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.cube.cubetravel.data.beans.AttractionsBean

/** 景點列表專用的 DiffCallback */
class AttractionsListDiffCallback : DiffUtil.ItemCallback<AttractionsBean>() {
    override fun areItemsTheSame(oldItem: AttractionsBean, newItem: AttractionsBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AttractionsBean, newItem: AttractionsBean): Boolean {
        return oldItem == newItem
    }
}