package com.data.newsapps.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.data.newsapps.data.api.ApiHelper
import com.data.newsapps.data.repository.MainRepository

/**
 * Created by sweety on 02,December,2021
 */
class MainViewModelFactory(private val apiHelper: ApiHelper,private val context: Context):ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(MainRepository(apiHelper), context as Application) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }

}