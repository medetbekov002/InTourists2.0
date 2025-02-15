package com.dev.intourist.domain.usecase.validate

import android.content.Context
import android.util.Patterns
import com.dev.intourist.presentation.ui.model.validation.ValidationResult
import com.dev.intourist.presentation.ui.model.validation.Validator
import com.dev.intourist.R

class ValidateEmail constructor(
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