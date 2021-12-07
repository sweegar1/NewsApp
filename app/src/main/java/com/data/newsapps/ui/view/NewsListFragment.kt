package com.data.newsapps.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.newsapps.R
import com.data.newsapps.data.api.ApiHelper
import com.data.newsapps.data.api.RetrofitBuilder
import com.data.newsapps.data.model.Articles
import com.data.newsapps.data.model.ResponseEverything
import com.data.newsapps.databinding.FragmentNewsListBinding
import com.data.newsapps.ui.adapter.NoteRecyclerAdapter
import com.data.newsapps.ui.viewmodel.MainViewModel
import com.data.newsapps.ui.viewmodel.MainViewModelFactory
import com.data.newsapps.utils.Resource
import com.data.newsapps.utils.Status


/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {
    private lateinit var binding: FragmentNewsListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        binding.recycler.layoutManager = LinearLayoutManager(activity)

    }

    fun observeData() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService), requireActivity().applicationContext)
        ).get(MainViewModel::class.java)

        viewModel.getNewsFeed().observe(getViewLifecycleOwner(),
            Observer<Resource<ResponseEverything>> {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.recycler.visibility = View.VISIBLE
                        it.data?.let {
                            binding.recycler.adapter = activity?.let { it1 ->
                                NoteRecyclerAdapter(
                                    viewModel,
                                    it.articles, it1.applicationContext
                                )
                            }
                            binding.recycler
                                .adapter?.notifyDataSetChanged()
                        }
                    }

                    Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }

                    Status.ERROR -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.message.text = it.message
                        binding.message.visibility = View.VISIBLE
                    } Status.NONETWORK -> {
                        binding.progressCircular.visibility = View.GONE
                        binding.message.text = it.message
                        binding.message.visibility = View.VISIBLE
                    }
                }
            })


        viewModel.articalData.observe(getViewLifecycleOwner(), Observer {
            it?.let {
                addNewsDetailsFragment(it)
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            NewsListFragment().apply {

            }
    }

    fun addNewsDetailsFragment(articalData: Articles?) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsDetailsFragment.newInstance(articalData))
            .addToBackStack(null)
            .commit()
    }
  }