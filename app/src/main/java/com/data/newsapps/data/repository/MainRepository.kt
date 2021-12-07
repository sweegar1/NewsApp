package com.data.newsapps.data.repository

import com.data.newsapps.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getNewsFeed() = apiHelper.getNewsFeed()
}