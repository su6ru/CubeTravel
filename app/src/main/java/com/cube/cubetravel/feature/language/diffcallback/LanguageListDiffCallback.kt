package com.cube.cubetravel.feature.language.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean

/** 語言列表專用的 DiffCallback */
class LanguageListDiffCallback : DiffUtil.ItemCallback<LanguageBean>() {
    override fun areItemsTheSame(oldItem: LanguageBean, newItem: LanguageBean): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: LanguageBean, newItem: LanguageBean): Boolean {
        return oldItem == newItem
    }
}