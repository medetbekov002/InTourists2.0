package com.dev.intourist.ui.validation.usecase

import android.content.Context
import com.dev.intourist.ui.validation.ValidationResult
import com.dev.intourist.ui.validation.Validator
import com.dev.intourist.R
import javax.inject.Inject

class ValidatePassword @Inject constructor(
    private val context: Context,
) : Validator {

    override operator fun invoke(text: String): ValidationResult = when {
        text.isEmpty() -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.field_must_be_filled),
            )
        }

        text.length < 6 -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.password_must_not_be_less_than_6_characters)
            )
        }

        else -> {
            ValidationResult(
                isSuccessful = true,
            )
        }
    }
}