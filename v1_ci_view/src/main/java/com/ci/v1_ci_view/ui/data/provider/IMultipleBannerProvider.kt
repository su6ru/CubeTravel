package com.ci.v1_ci_view.ui.data.provider
/** 使用MultipleBanner 時,
 * 其資料類一定要繼承此interface,
 * 並將getImageUrl設定回傳預計顯示之圖片的url,
 * 否則圖片不會正常顯示  */
interface IMultipleBannerProvider {
    fun getImageUrl(): String

}