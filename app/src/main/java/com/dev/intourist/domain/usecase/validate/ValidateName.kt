package com.dev.intourist.domain.usecase.validate

import android.content.Context
import com.dev.intourist.presentation.ui.model.validation.ValidationResult
import com.dev.intourist.presentation.ui.model.validation.Validator
import com.dev.intourist.R

class ValidateName constructor(
    private val context: Context,
) : Validator {

    override operator fun invoke(text: String): ValidationResult = when {
        text.isEmpty() -> {
            ValidationResult(
                isSuccessful = false,
                context.getString(R.string.field_must_be_filled)
            )
        }

        text.matches(Regex(".*\\p{InCyrillic}.*")) -> {
            ValidationResult(
                isSuccessful = false,
                context.getString(R.string.write_in_latin)
            )
        }

        !text.matches(Regex("^[\\p{L} ]+$")) -> {
            ValidationResult(
                isSuccessful = false,
                context.getString(R.string.incorrect_name)
            )
        }

        text.length < 2 -> {
            ValidationResult(
                isSuccessful = false,
                context.getString(R.string.name_must_contain_at_least_2_characters)
            )
        }

        else -> {
            ValidationResult(
                isSuccessful = true,
            )
        }
    }
}