package com.cube.cubetravel.feature.main.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.ci.v1_ci_view.ui.view.checkbox.CICheckBox
import com.cube.cubetravel.R
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.databinding.ItemNewsListBinding

/** 最新消息列表 的 ViewHolder */
class NewsListViewHolder(val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root) {

    // MARK: - ========================== View
    init {
        val layoutParams = RecyclerView
            .LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
        val margin = CIResourceUtil.getPxByDp(8f)
        layoutParams.setMargins(margin,margin,margin,margin)

        itemView.layoutParams = layoutParams
    }
    // MARK: - ========================== View


    // MARK: - ========================== Method
    fun bindData(newsBean: NewsBean){
        binding.apply {
            this.newsBean = newsBean
            executePendingBindings()
        }

    }


}