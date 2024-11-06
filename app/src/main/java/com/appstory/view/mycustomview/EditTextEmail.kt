package com.appstory.view.mycustomview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import com.appstory.R
import com.appstory.view.InputValidator

class EditTextEmail @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    init {
        doOnTextChanged { text, _, _, _ ->
            val email = text.toString()
            error = if (!InputValidator.isEmailValid(email)) {
                context.getString(R.string.email_invalid_format)
            } else {
                null
            }
        }
    }
}