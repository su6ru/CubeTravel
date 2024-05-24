package com.ci.v1_ci_view.ui.view.layout

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.listener.IOnOptionListener

class CISwipeRefreshLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
): SwipeRefreshLayout(context,attrs) {
    companion object{

        /** dataBinding用的 當CISwipeRefreshLayout滑到 最頂部 的  監聽處理方法 */
        @JvmStatic
        @BindingAdapter("onRefreshed")
        fun setOnRefreshListener(swipeRefreshLayout: CISwipeRefreshLayout, listener: IOnOptionListener<Void>) {
            swipeRefreshLayout.setOnRefreshListener{
                swipeRefreshLayout.isRefreshing = false
                listener.onExecute(null)
            }

        }

    }

    override fun setRefreshing(refreshing: Boolean) {
        super.setRefreshing(refreshing)
        // 如果不需要動畫，立即停止刷新動畫
        if (!refreshing) {
            try {
                val setRefreshing = SwipeRefreshLayout::class.java.getDeclaredMethod(
                    "setRefreshing",
                    Boolean::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType
                )
                setRefreshing.isAccessible = true
                setRefreshing.invoke(this, false, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}