package com.ci.v1_ci_view.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.ci.v1_ci_view.ui.view.alert.CILoadingAlert
import com.google.gson.Gson

/** CIActivity */
abstract class CIActivity<T> : AppCompatActivity() {
    // MARK:- ========================== Define
    companion object{
        val INTENT_STRING = "INTENT_STRING"

        fun startActivity(activity: CIActivity<*>,cls: Class<out CIActivity<*>?>?,request: Any?){
            val intent = Intent(activity,cls)
            if (request != null){
                intent.putExtra(INTENT_STRING,Gson().toJson(request))
            }

            activity.startActivity(intent)
        }
        /** 原本的startActivityForResult已經棄用
         * 現在需要使用 ActivityResultLauncher<Intent>
         */
        fun startResultActivity(activity: CIActivity<*>,cls: Class<out CIActivity<*>?>?,activityResultLauncher: ActivityResultLauncher<Intent>,request: Any?){
            val intent = Intent(activity,cls)
            if (request != null){
                intent.putExtra(INTENT_STRING,Gson().toJson(request))
            }

            activityResultLauncher.launch(intent)
        }
    }



    // MARK:- ========================== View
    /** 用於顯示Loading中的 Alert */
    private var mLoadingAlert : CILoadingAlert? = null
    /** 取得activity最外圍的主layout */
    abstract fun getSupperLayout():FrameLayout
    // MARK:- ========================== Data
    /** Loading 狀態的count ，當大於0代表Loading中 */
    private var mLoadingAlertCount = 0
    fun isLoading() : Boolean{
        return mLoadingAlertCount != 0
    }
    /** 設定是否顯示Loading */
    fun setLoading(enable: Boolean){
        if (enable){
            showLoadingAlert()
        }else{
            hideLoadingAlert()
        }

    }


    fun getIntentData(clazz: Class<T>): T? {
        return Gson().fromJson(intent.getStringExtra(INTENT_STRING), clazz)
    }
    // MARK:- ========================== Event

    // MARK:- ========================== Method
    /** 顯示 Toast */
    fun showToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
    /** 顯示Loading標誌 */
    private fun showLoadingAlert(){

        mLoadingAlertCount += 1

        Log.d("loading", "show")

        if (mLoadingAlertCount == 0) {
            return
        }

        if (mLoadingAlert == null) {
            mLoadingAlert = CILoadingAlert(this)
        }

        if (!mLoadingAlert!!.mIsShow) {
            val view = getSupperLayout()
            if (view is FrameLayout) {
                mLoadingAlert!!.show(view)
            }
        }
    }
    /** 隱藏Loading標誌 */
    private fun hideLoadingAlert(){
        mLoadingAlertCount -= 1

        Log.d("loading", "hide")

        if (mLoadingAlertCount != 0) {
            return
        }

        if (mLoadingAlert == null) {
            return
        }

        mLoadingAlert!!.hide()
    }


}