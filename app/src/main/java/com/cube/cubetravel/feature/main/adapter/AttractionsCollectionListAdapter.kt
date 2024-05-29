package com.cube.cubetravel.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ci.v1_ci_view.ui.listener.IOnOptionCheckedChangedListener
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.databinding.ItemAttractionsCollectionListBinding
import com.cube.cubetravel.databinding.ItemAttractionsListBinding
import com.cube.cubetravel.feature.main.diffcallback.AttractionsCollectionListDiffCallback
import com.cube.cubetravel.feature.main.diffcallback.AttractionsListDiffCallback
import com.cube.cubetravel.feature.main.viewholder.AttractionsCollectionListViewHolder
import com.cube.cubetravel.feature.main.viewholder.AttractionsListViewHolder

/** 景點列表專用的 ListAdapter */
class AttractionsCollectionListAdapter(attractionsCollectionListDiffCallback: AttractionsCollectionListDiffCallback
                                       , val onItemClickListener: IOnOptionListener<AttractionsCollectionBean>
                                       , val onCheckedChangeListener: IOnOptionCheckedChangedListener<AttractionsCollectionBean>
) : ListAdapter<AttractionsCollectionBean, AttractionsCollectionListViewHolder>(attractionsCollectionListDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsCollectionListViewHolder {

        val binding = ItemAttractionsCollectionListBinding.inflate(LayoutInflater.from(parent.context))

        val holder = AttractionsCollectionListViewHolder(binding)
        //當點擊 itemView
        holder.itemView.setOnClickListener {
            onItemClickListener.onExecute(getItem(holder.adapterPosition))
        }
        //當點擊 收藏
        holder.mCollectionCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            onCheckedChangeListener.onExecute(getItem(holder.adapterPosition),isChecked)
        }
        return holder
    }

    override fun onBindViewHolder(holder: AttractionsCollectionListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}