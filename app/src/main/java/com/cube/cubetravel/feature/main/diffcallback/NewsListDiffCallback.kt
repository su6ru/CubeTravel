package com.cube.cubetravel.feature.main.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean

/** 最新消息列表專用的 DiffCallback */
class NewsListDiffCallback : DiffUtil.ItemCallback<NewsBean>() {
    override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem == newItem
    }
}