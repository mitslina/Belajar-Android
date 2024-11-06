package com.appstory.view.liststory

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.appstory.R
import com.appstory.databinding.ActivityListStoryBinding
import com.appstory.view.ViewModelFactory
import com.appstory.view.addstory.AddStoryActivity
import com.appstory.view.liststory.adapter.LoadingStateAdapter
import com.appstory.view.liststory.adapter.StoryListAdapter
import com.appstory.view.logout.LogoutActivity
import com.appstory.view.logout.LogoutViewModel
import com.appstory.view.maps.MapsActivity
import com.appstory.view.welcome.WelcomeActivity

class ListStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListStoryBinding
    private lateinit var adapter: StoryListAdapter
    private val viewModel: ListStoryViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val viewModelAuth by viewModels<LogoutViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkSession()
        setupView()
        binding.rvStory.layoutManager = LinearLayoutManager(this)

        getStories()
    }

    private fun checkSession() {
        viewModelAuth.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }
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
        supportActionBar?.title = getString(R.string.list_story)

        binding?.fabAddStory?.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }
    }

    private fun getStories() {
        adapter = StoryListAdapter()
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.story.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_story_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                val moveIntent = Intent(this@ListStoryActivity, LogoutActivity::class.java)
                startActivity(moveIntent)
                return true
            }
            R.id.action_language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                return true
            }
            R.id.action_maps -> {
                val moveIntent = Intent(this@ListStoryActivity, MapsActivity::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}