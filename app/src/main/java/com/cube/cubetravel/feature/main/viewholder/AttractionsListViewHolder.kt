package com.cube.cubetravel.feature.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.view.checkbox.CICheckBox
import com.cube.cubetravel.R
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.databinding.ItemAttractionsListBinding
/** 景點列表 的 ViewHolder */
class AttractionsListViewHolder(val binding: ItemAttractionsListBinding) : RecyclerView.ViewHolder(binding.root) {

    // MARK: - ========================== View
    init {
        itemView.layoutParams = RecyclerView
            .LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
    }
    // MARK: - ========================== View
    /** 收藏 */
    val mCollectionCheckbox: CICheckBox by lazy {
        itemView.findViewById(R.id.checkbox_collection)
    }

    // MARK: - ========================== Method
    fun bindData(attractionsBean: AttractionsBean){
        binding.attractionsBean = attractionsBean
        binding.executePendingBindings()
    }


}