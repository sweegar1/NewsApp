package com.data.newsapps.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.data.newsapps.ui.viewmodel.MainViewModel
import com.data.newsapps.R
import com.data.newsapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    // view binding for the activity
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
           addNewsListFragment()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


     fun addNewsListFragment() {
        this.supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsListFragment.newInstance())
            .commit()   }


}