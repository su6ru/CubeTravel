package com.ci.v1_ci_view.ui.util

import android.app.Activity
import android.content.Intent
import android.net.Uri

class CIIntentUtil {
    companion object {
        /** 開啟電話簿,並將 "電話號碼" 設置為 前置撥打狀態
         *
         * 需於AndroidManifest設置 <queries> 否則 Intent.resolveActivity 必為null
         */
        fun openPhoneCall(activity: Activity,phoneNumber: String){
            val phoneNumberString = String.format("tel:%s", phoneNumber)
            val uri = Uri.parse(phoneNumberString)
            val intent = Intent(Intent.ACTION_DIAL,uri)
            if (intent.resolveActivity(activity.packageManager) != null){
                activity.startActivity(intent)
            }
        }
        /** 開啟 google定位 的方法
         *
         * 需於AndroidManifest設置 <queries> 否則 Intent.resolveActivity 必為null
         */
        fun openGoogleMapToLocation(activity: Activity,address: String,latitude: Double,longitude: Double){
            //若 地址 搜尋不到 則以 經緯度 為主
            val geoText = String.format("geo:%s,%s?q=%s&z=17",latitude,longitude,address)
            val uri = Uri.parse(geoText)
            //開啟googleMap 並選取 地址 或 座標marker
            val intent = Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
            if (intent.resolveActivity(activity.packageManager) != null){
                activity.startActivity(intent)
            }
        }
        /** 開啟 Email寄送 的方法
         *
         * 需於AndroidManifest設置 <queries> 否則 Intent.resolveActivity 必為null
         */
        fun openMailWrite(activity: Activity,emailAddress: String){
            val uri = Uri.parse("mailto:")

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(uri)

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            if (intent.resolveActivity(activity.packageManager) != null){
                activity.startActivity(intent)
            }
        }
        /** 透過瀏覽器 開啟 網頁
         *
         * 需於AndroidManifest設置 <queries> 否則 Intent.resolveActivity 必為null
         */
        fun openWeb(activity: Activity,urlString: String){
            val uri = Uri.parse(urlString)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            if (intent.resolveActivity(activity.packageManager) != null){
                activity.startActivity(intent)
            }
        }

    }
}