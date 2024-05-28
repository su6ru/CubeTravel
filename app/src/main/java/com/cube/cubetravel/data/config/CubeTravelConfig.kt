package com.cube.cubetravel.data.config

class CubeTravelConfig{

    companion object{
        const val URL_TRAVEL = "https://www.travel.taipei/open-api/"

        /** 目前API 每頁資料量 */
        const val COUNT_EVERY_PAGE_DATA = 30
        /** API 預設初始頁碼 */
        const val PAGE_DEFAULT = "1"


        /** 官網 */
        const val WEB_URL = "https://www.travel.taipei/"


        /** 預設語系設定 */
        const val DEFAULT_LANGUAGE_LANGUAGE = "正體中文"
        const val DEFAULT_LANGUAGE_VALUE = "zh-tw"
        const val DEFAULT_LANGUAGE_IMAGE = "ic_tw"
        const val DEFAULT_LANGUAGE_APP_RESOURCE_CODE = "zh-rTW"

    }
}