package com.data.newsapps.data.api

import com.data.newsapps.data.model.ResponseEverything
import com.data.newsapps.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNewsFeed(
        @Query(AppConstants.PARAM_TOPIC) type: String,
        @Query(AppConstants.PARAM_API_KEY) accessKey: String
    ): ResponseEverything

}