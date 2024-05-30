package com.cube.cubetravel.data.network.call

import android.content.res.Resources
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.network.data.BaseApiData
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 /** ApiCall */
class TravelApiCall<T: BaseApiData.Response<*>>(
    private val successListener: IOnOptionListener<T>,
    private val failListener: IOnOptionListener<String>,
    private val completeListener: IOnOptionListener<Void>): Callback<T> {
    private val mContext = CubeTravelApplication.INSTANCE.baseContext
    override fun onResponse(p0: Call<T>, p1: Response<T>) {

        if (p1.isSuccessful) {

            onCallSuccess(p1.body()!!)
        }else{
            var message = p1.message()
            if (message.isNullOrEmpty()){
                message = mContext.getString(R.string.msg_data_abnormal)
            }
            onCallFail(message)
        }
    }
    override fun onFailure(p0: Call<T>, p1: Throwable) {
        var message = p1.message.toString()
        if (message.isEmpty()){
            message = mContext.getString(R.string.msg_data_abnormal)
        }
        onCallFail(message)
    }
    /**當 回傳 成功*/
    fun onCallSuccess(data: T){
        successListener.onExecute(data)
        completeListener.onExecute(null)
    }
    /**當 回傳 失敗*/
    fun onCallFail(msg: String){
        failListener.onExecute(msg)
        completeListener.onExecute(null)
    }



}