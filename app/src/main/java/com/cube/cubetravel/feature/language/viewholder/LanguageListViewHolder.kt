package com.cube.cubetravel.feature.language.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.databinding.ItemLanguageListBinding

/** 語言列表 的 ViewHolder */
class LanguageListViewHolder(val binding: ItemLanguageListBinding) : RecyclerView.ViewHolder(binding.root) {

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
    fun bindData(languageBean: LanguageBean){
        binding.apply {
            this.languageBean = languageBean
            executePendingBindings()
        }

    }


}