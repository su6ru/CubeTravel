package com.cube.cubetravel.data.network

import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ITravelApiService {
    companion object{
        fun invoke(): ITravelApiService {

            val okHttpClient: OkHttpClient = OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(Interceptor{chain: Interceptor.Chain ->
                    val original = chain.request()

                    val requestBuilder = original
                        .newBuilder()
                        .header("Accept","application/json")
                        .method(original.method,original.body)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }).build()
            return Retrofit.Builder()
                .baseUrl(CubeTravelConfig.URL_TRAVEL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ITravelApiService::class.java)
        }
    }

    // MARK: - ========================== 基本api
    /** 景點列表
     * @param language 語系
     * @param categoryIds 類別ID
     * @param nlat 緯度(可能因後台資料本身不多,所以實測後我認為是多餘的)
     * @param elong 經度(可能因後台資料本身不多,所以實測後我認為是多餘的)
     * @param page 當前請求的頁碼,目前30筆資料1頁。
     */
    @GET("{lang}/Attractions/All?")
    fun getAttractionsList(@Path("lang") language: String?
                           ,@Query("categoryIds") categoryIds: String?
                           ,@Query("nlat") nlat: String?
                           ,@Query("elong") elong: String?
                           ,@Query("page") page: String?)
    : Call<ApiBase.GetAttractionsList.Response>

    /** 最新消息列表
     * @param language 語系
     * @param begin 開始時間，格式 yyyy-MM-dd
     * @param end 結束時間，格式 yyyy-MM-dd
     * @param page 當前請求的頁碼,目前30筆資料1頁。
     */
    @GET("{lang}/Events/News?")
    fun getNewsList(@Path("lang") language: String?
                           ,@Query("begin") begin: String?
                           ,@Query("end") end: String?
                           ,@Query("page") page: String?)
            : Call<ApiBase.GetNewsList.Response>

}