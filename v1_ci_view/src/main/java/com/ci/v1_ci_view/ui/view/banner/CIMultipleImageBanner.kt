package com.ci.v1_ci_view.ui.view.banner

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DispatchMode
import androidx.databinding.BindingAdapter
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.data.diffcallback.MarginImageBannerDiffCallback
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.ci.v1_ci_view.ui.view.banner.adapter.CIMultipleImageBannerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs
/** 客製化Banner,專門用於顯示圖片,且一次會顯示3個item ,並隨著設定秒數自動切換到下一個 */
class CIMultipleImageBanner<T : IMultipleBannerProvider> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs),DefaultLifecycleObserver{


    companion object{
        final val TIME = 3000L

        /**
         * 用於在 [CIMultipleImageBanner] 上設置項目點擊監聽器的 BindingAdapter。
         *
         * 通過使用 `app:onViewPagerItemClick` 屬性，該適配器允許您直接從 XML 佈局文件中綁定一個 [IOnOptionListener] 到 [CIMultipleImageBanner]。
         *
         * 當 Banner 內的任一子視圖被點擊時，提供的 [IOnOptionListener] 的 [IOnOptionListener.onExecute] 方法將被調用。
         *
         * @param view 需要設置點擊監聽器的 [CIMultipleImageBanner]。
         * @param listener 當項目被點擊時將被通知的 [IOnOptionListener]。
         */
        @JvmStatic
        @BindingAdapter("onViewPagerItemClick")
        fun <T: IMultipleBannerProvider> setOnViewPagerItemClickListener(view: CIMultipleImageBanner<T>, listener: IOnOptionListener<T>)  {
            view.mOnBannerItemClick = object :IOnOptionListener<T>{
                override fun onExecute(option: T?) {
                    listener.onExecute(option)
                }
            }
        }

    }
    // MARK: - ========================== Life
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        /** 每當返回該畫面則開始 */
        runAutoScrollChange()

    }
    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        //取消 自動切換item的執行序
        mJob?.cancel();
    }

    // MARK:-================= View
    /** banner的主體 */
    private val mImageViewPager2 : ViewPager2 by lazy {
        findViewById(R.id.viewpager2)
    }
    /** 最底部radiobutton 列 */
    private val mRadioGroup : RadioGroup by lazy {
        findViewById(R.id.radio_group_marker)
    }
    // MARK:-================= Data
    /** Banner的 Adapter */
    private lateinit var mViewPagerAdapter: CIMultipleImageBannerAdapter<T>

    private val mCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var mJob: Job? = null
    fun runAutoScrollChange(){
        mJob = mCoroutineScope.launch {
            while (true){

                delay(TIME)

                if (mViewPagerAdapter.itemCount != 0){
                    var nextPosition = mImageViewPager2.currentItem + 1
                    val totalSize = mViewPagerAdapter.itemCount
                    if (nextPosition == totalSize){
                        nextPosition = 0
                    }
                    mImageViewPager2.setCurrentItem(nextPosition, true)
                }
            }


        }

    }

    // MARK:-================= Event
    /** [CIMultipleImageBanner] 子item 點擊監聽器 */
    var mOnBannerItemClick: IOnOptionListener<T>? = null
    /** 當發生頁面切換 (無論是透過點擊 上一個 .下一個 .左滑動 .右滑動) */
    fun onViewPagerChanged(position: Int){
        for (i in 0 until mRadioGroup.childCount) {
            val button = mRadioGroup.getChildAt(i) as RadioButton
            button.isChecked = (i == position)
        }
    }
    /** 當點擊banner內部item */
    fun onBannerItemClick(option : T?){
        mOnBannerItemClick?.onExecute(option)
    }
    // MARK:-================= Method
    init {
        inflate(context, R.layout.view_ci_multiple_image_banner, this)

        if (context is LifecycleOwner) {
            (context as LifecycleOwner).lifecycle.addObserver(this)
        }
        val marginImageBannerDiffCallback = MarginImageBannerDiffCallback<T>()

        mViewPagerAdapter = CIMultipleImageBannerAdapter<T>(marginImageBannerDiffCallback)

        mImageViewPager2.adapter = mViewPagerAdapter
        mImageViewPager2.offscreenPageLimit = 3
        mImageViewPager2.clipToPadding = false
        mImageViewPager2.clipChildren = false
        mImageViewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        mImageViewPager2.setPageTransformer(createTransformer())
        // ===== Event

        //onBannerItemClick
        mViewPagerAdapter.mBannerItemClickListener = object : IOnOptionListener<T>{
            override fun onExecute(option: T?) {
                onBannerItemClick(option)
            }

        }
        //onViewPagerChanged
        mImageViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onViewPagerChanged(position)
            }
        })

    }
    /** Adapter 資料變動 */
    fun submitList(dataList: List<T>) {
        mViewPagerAdapter.submitList(dataList)
        generateRadiobutton(dataList.size)

    }
    /** 頁面轉換效果 */
    private fun createTransformer(): CompositePageTransformer{
        val transformer = CompositePageTransformer()
        //ui間距
        transformer.addTransformer(MarginPageTransformer(40))
        //頁面滑動時 的ui轉換效果
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        return transformer
    }
    /** 生成底部radiobutton 按鈕 */
    private fun generateRadiobutton(quantity : Int){
        mRadioGroup.clearCheck()
        mRadioGroup.removeAllViews()
        if (quantity == 0){
            return
        }
        for (i in 0 until quantity){
            val radioButton = RadioButton(context)
            radioButton.width = CIResourceUtil.getPxByDp(10f)
            radioButton.height = CIResourceUtil.getPxByDp(10f)
            radioButton.background = ContextCompat.getDrawable(context,R.drawable.selector_banner_amount)
            radioButton.buttonDrawable = null
            radioButton.isChecked = i == 0

            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 16, 0)  // left, top, right, bottom
            radioButton.layoutParams = params

            mRadioGroup.addView(radioButton)
        }

    }
}