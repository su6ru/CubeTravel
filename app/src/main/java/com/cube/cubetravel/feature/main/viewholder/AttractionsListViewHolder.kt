package com.cube.cubetravel.feature.main.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.ci.v1_ci_view.ui.view.checkbox.CICheckBox
import com.cube.cubetravel.R
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.databinding.ItemAttractionsListBinding
/** 景點列表 的 ViewHolder */
class AttractionsListViewHolder(val binding: ItemAttractionsListBinding) : RecyclerView.ViewHolder(binding.root) {

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
    /** 收藏 */
    val mCollectionCheckbox: CICheckBox by lazy {
        itemView.findViewById(R.id.checkbox_collection)
    }

    // MARK: - ========================== Method
    fun bindData(attractionsBean: AttractionsBean){
        binding.apply {
            this.attractionsBean = attractionsBean
            executePendingBindings()
            //如果圖片url為空 則 隱藏ImageView
            image.visibility = if (attractionsBean.firstImageUrl.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
        }

    }


}