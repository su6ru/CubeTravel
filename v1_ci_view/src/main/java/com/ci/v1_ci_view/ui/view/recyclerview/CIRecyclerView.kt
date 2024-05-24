package com.ci.v1_ci_view.ui.view.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.view.banner.CIMultipleImageBanner

/** RecyclerView  */
class CIRecyclerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RecyclerView(context,attributeSet) {

    companion object{
        /** dataBinding用的 當CIRecyclerView滑到 最底部 的 監聽處理方法 */
        @JvmStatic
        @BindingAdapter("onBottomReached")
        fun setOnBottomReachedListener(recyclerView: CIRecyclerView,  listener: IOnOptionListener<Void>) {

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    //當滑動到最底部
                    if (!recyclerView.canScrollVertically(1)){
                        listener.onExecute(null)
                    }
                }
            })
        }
        /** dataBinding用的 當CIRecyclerView滑到 最頂部 的  監聽處理方法 */
        @JvmStatic
        @BindingAdapter("onTopReached")
        fun setOnTopReachedListener(recyclerView: CIRecyclerView,  listener: IOnOptionListener<Void>) {

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    //當滑動到最頂部
                    if (!recyclerView.canScrollVertically(-1)){
                        listener.onExecute(null)
                    }
                }
            })
        }

    }

}