package com.appstory.view.detailstory

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appstory.R
import com.appstory.databinding.ActivityDetailStoryBinding
import com.appstory.data.network.respons.StoryResponse
import com.appstory.view.ViewModelFactory
import com.bumptech.glide.Glide

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding
    private var storyId: String? = null

    private val viewModel by viewModels<DetailStoryViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        checkStoryId()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.addCallback(this) {
                    finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkStoryId() {
        storyId = intent.getStringExtra(EXTRA_STORY_ID)

        storyId?.let {
            viewModel.getDetailStory(it)
            observeViewModel()
        } ?: run {
            Toast.makeText(this, getString(R.string.detail_story_not_found), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        viewModel.detailStory.observe(this) { detailStory ->
            if (detailStory == null) {
                Toast.makeText(this, getString(R.string.detail_story_not_found), Toast.LENGTH_SHORT).show()
            } else {
                setDetailStory(detailStory)
            }
        }

        viewModel.message.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDetailStory(story: StoryResponse) {
        binding.tvDetailName.text = story.name
        binding.tvDetailDescription.text = story.description
        if (!story.photoUrl.isNullOrEmpty()) {
            Glide.with(binding.ivDetailPhoto.context)
                .load(story.photoUrl)
                .into(binding.ivDetailPhoto)
        } else {
            binding.ivDetailPhoto.scaleType = ImageView.ScaleType.FIT_CENTER
            binding.ivDetailPhoto.setImageDrawable(
                ContextCompat.getDrawable(binding.ivDetailPhoto.context, R.drawable.ic_image_not_supported_24)
            )
        }
        binding.tvDetailCreatedAt.text = story.createdAt
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_STORY_ID = "extra_story_id"
    }

}