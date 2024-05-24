package com.ci.v1_ci_view.ui.data.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
/** Banner-同時顯示3子項目的版本 專用ViewHolder */
class MarginImageBannerDiffCallback<T: IMultipleBannerProvider>: DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.getImageUrl() == newItem.getImageUrl()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.getImageUrl() == newItem.getImageUrl()
    }
}