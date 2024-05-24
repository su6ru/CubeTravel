package com.ci.v1_ci_view.ui.view.banner.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
import com.ci.v1_ci_view.ui.view.imageview.CIImageView
/** Banner-同時顯示3子項目的版本 專用ViewHolder */
class MultipleBannerViewHolder<T: IMultipleBannerProvider>(itemView: View): RecyclerView.ViewHolder(itemView) {
    init {

    }
    /** 主圖 */
    private val mImageView: CIImageView by lazy {
        itemView.findViewById(R.id.imageView)
    }

    fun loadData(t: T){
        mImageView.setImageView(t.getImageUrl())
    }
}
