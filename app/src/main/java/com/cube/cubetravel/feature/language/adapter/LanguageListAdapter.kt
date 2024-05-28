package com.cube.cubetravel.feature.language.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.databinding.ItemLanguageListBinding
import com.cube.cubetravel.feature.language.diffcallback.LanguageListDiffCallback
import com.cube.cubetravel.feature.language.viewholder.LanguageListViewHolder

/** 語言列表專用的 ListAdapter */
class LanguageListAdapter(languageListDiffCallback: LanguageListDiffCallback
                          , val onItemClickListener: IOnOptionListener<LanguageBean>
) : ListAdapter<LanguageBean, LanguageListViewHolder>(languageListDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageListViewHolder {

        val binding = ItemLanguageListBinding.inflate(LayoutInflater.from(parent.context))

        val holder = LanguageListViewHolder(binding)
        //當點擊 itemView
        holder.itemView.setOnClickListener {
            onItemClickListener.onExecute(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: LanguageListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}