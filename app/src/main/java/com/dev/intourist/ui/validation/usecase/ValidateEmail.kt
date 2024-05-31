package com.dev.intourist.ui.validation.usecase

import android.content.Context
import android.util.Patterns
import com.dev.intourist.ui.validation.ValidationResult
import com.dev.intourist.ui.validation.Validator
import com.dev.intourist.R
import javax.inject.Inject

class ValidateEmail @Inject constructor(
    private val context: Context,
) : Validator {

    override operator fun invoke(text: String): ValidationResult = when {
        text.isEmpty() -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.field_must_be_filled)
            )
        }

        !Patterns.EMAIL_ADDRESS.matcher(text).matches() -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.invalid_email)
            )
        }

        else -> {
            ValidationResult(
                isSuccessful = true
            )
        }
    }
}