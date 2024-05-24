package com.ci.v1_ci_view.ui.view.banner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.view.banner.viewholder.MultipleBannerViewHolder

class CIMultipleImageBannerAdapter<T: IMultipleBannerProvider>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, MultipleBannerViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleBannerViewHolder<T> {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ci_multiple_image_banner, parent, false)
        val holder = MultipleBannerViewHolder<T>(view)

        if (mBannerItemClickListener != null){
            holder.itemView.setOnClickListener {
                mBannerItemClickListener.onExecute(getItem(holder.adapterPosition))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MultipleBannerViewHolder<T>, position: Int) {
        holder.loadData(getItem(position))
    }
    lateinit var mBannerItemClickListener: IOnOptionListener<T>
}