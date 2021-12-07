package com.data.newsapps.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.data.newsapps.data.model.Articles
import com.data.newsapps.databinding.FragmentNewsDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var articalData: Articles? = null
    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articalData = it.get(ARG_PARAM1) as Articles?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false);
    setValues()
        return binding.getRoot();

    }

    private fun setValues() {

        articalData.let {
            binding.title.text = articalData!!.title
            binding.content.text = articalData!!.content
            binding.description.text = articalData!!.description
            binding.author.text = articalData!!.author
            if (!articalData!!.urlToImage.isEmpty()) {
                Glide.with(binding.imageViewAvatar.context)
                    .load(articalData!!.urlToImage)
                    .into(binding.imageViewAvatar)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment NewsDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
//        fun newInstance(param1: Articals) =
        fun newInstance(articalData: Articles?) =
            NewsDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, articalData)

                }
            }
    }
}