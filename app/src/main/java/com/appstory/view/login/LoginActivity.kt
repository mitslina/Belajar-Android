package com.appstory.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.appstory.R
import com.appstory.databinding.ActivityLoginBinding
import com.appstory.view.InputValidator
import com.appstory.view.ViewModelFactory
import com.appstory.view.liststory.ListStoryActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
        observeViewModel()
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
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (InputValidator.isEmailValid(email) && InputValidator.isPasswordValid(password)) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(this, getString(R.string.email_password_invalid), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvTitle = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val tvMessage = ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val tvEmail = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val etEmailLayout = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val tvPassword = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val etPasswordLayout = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val loginButton = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                tvTitle,
                tvMessage,
                tvEmail,
                etEmailLayout,
                tvPassword,
                etPasswordLayout,
                loginButton
            )
            startDelay = 100
        }.start()
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        viewModel.isError.observe(this, Observer { isError ->
            isError?.let { error ->
                viewModel.message.observe(this, Observer { message ->
                    if (error) {
                        showAlertDialog(getString(R.string.waduw), message, getString(R.string.coba_lagi))
                    } else {
                        showAlertDialog(getString(R.string.yeah), message, getString(R.string.lanjut)) {
                            moveToList()
                        }
                    }
                })
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loginButton.isEnabled = !isLoading
        binding.loginButton.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        binding.loginButton.text = if (isLoading) getString(R.string.memuat) else getString(R.string.login)
    }

    private fun showAlertDialog(title: String, message: String?, buttonText: String, onPositive: (() -> Unit)? = null) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonText) { dialog, _ ->
                onPositive?.invoke()
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun moveToList() {
        val intent = Intent(this, ListStoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}