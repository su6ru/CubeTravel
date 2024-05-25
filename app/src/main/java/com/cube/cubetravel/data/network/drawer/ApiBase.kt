package com.cube.cubetravel.data.network.drawer

import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.network.data.BaseApiData
import com.google.gson.annotations.SerializedName

class ApiBase {
    /** 景點列表 取得 */
    class GetAttractionsList{
        class Response : BaseApiData.Response<MutableList<AttractionsBean>>(){

        }
    }
    /** 最新消息列表 取得 */
    class GetNewsList{
        class Response : BaseApiData.Response<MutableList<NewsBean>>(){

        }
    }
}