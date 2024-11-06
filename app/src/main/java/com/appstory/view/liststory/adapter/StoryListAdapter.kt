package com.appstory.view.liststory.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appstory.R
import com.appstory.databinding.ItemStoryBinding
import com.appstory.data.network.respons.ListStoryItem
import com.appstory.view.detailstory.DetailStoryActivity
import com.bumptech.glide.Glide

class StoryListAdapter :
    PagingDataAdapter<ListStoryItem, StoryListAdapter.StoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class StoryViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: ListStoryItem) {
            binding.apply {
                if(!story.photoUrl.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(story.photoUrl)
                        .into(ivImage)
                }
                else {
                    ivImage.setScaleType(ImageView.ScaleType.FIT_CENTER)
                    ivImage.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_image_not_supported_24))
                }
                tvStoryName.text = story.name
                tvStoryDescription.text = story.description
            }
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailStoryActivity::class.java)
                intent.putExtra(DetailStoryActivity.EXTRA_STORY_ID, story.id)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}