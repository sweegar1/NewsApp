package com.data.newsapps.data.api

import com.data.newsapps.utils.AppConstants


class ApiHelper(private val apiService: ApiService) {
    suspend fun getNewsFeed()=apiService.getNewsFeed(AppConstants.NEWS_FEED_TOPIC,AppConstants.ACCESS_KEY)

}