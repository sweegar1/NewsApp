package com.data.newsapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.newsapps.R
import com.data.newsapps.data.model.Articles
import com.data.newsapps.databinding.ItemBinding
import com.data.newsapps.ui.viewmodel.MainViewModel


/**
 * Created by sweety on 02,December,2021
 */
class NoteRecyclerAdapter(
    val viewModel: MainViewModel,
    val arrayList: List<Articles>,
    val context: Context
) : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(arrayList[position]) {
                binding.title.text = arrayList[position].title
                binding.content.text = arrayList[position].content
                if (!urlToImage.isEmpty()) {
                    Glide.with(binding.imageViewAvatar.context)
                        .load(arrayList[position].urlToImage)
                        .into(binding.imageViewAvatar)
                }
                binding.newsCard.setOnClickListener {
                    viewModel.addNewsDetailsFragment(arrayList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, context.getString(R.string.empty_list), Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayList.size
    }

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)


}