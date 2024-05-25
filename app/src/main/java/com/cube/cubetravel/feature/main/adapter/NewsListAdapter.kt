package com.cube.cubetravel.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ci.v1_ci_view.ui.listener.IOnOptionCheckedChangedListener
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.databinding.ItemAttractionsListBinding
import com.cube.cubetravel.databinding.ItemNewsListBinding
import com.cube.cubetravel.feature.main.diffcallback.AttractionsListDiffCallback
import com.cube.cubetravel.feature.main.diffcallback.NewsListDiffCallback
import com.cube.cubetravel.feature.main.viewholder.AttractionsListViewHolder
import com.cube.cubetravel.feature.main.viewholder.NewsListViewHolder

/** 最新消息列表專用的 ListAdapter */
class NewsListAdapter(newsListDiffCallback: NewsListDiffCallback
                      , val onItemClickListener: IOnOptionListener<NewsBean>
) : ListAdapter<NewsBean, NewsListViewHolder>(newsListDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {

        val binding = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context))

        val holder = NewsListViewHolder(binding)
        //當點擊 itemView
        holder.itemView.setOnClickListener {
            onItemClickListener.onExecute(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}