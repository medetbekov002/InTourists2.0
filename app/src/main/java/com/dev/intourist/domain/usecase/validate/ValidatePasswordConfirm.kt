package com.dev.intourist.domain.usecase.validate

import android.content.Context
import com.dev.intourist.presentation.ui.model.validation.ValidationResult
import com.dev.intourist.R

// TODO: implement Validator
class ValidatePasswordConfirm constructor(
    private val context: Context,
) {

    operator fun invoke(password: String, confirmPassword: String): ValidationResult = when {
        password.isEmpty() && confirmPassword.isEmpty() -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.field_must_be_filled)
            )
        }

        password != confirmPassword -> {
            ValidationResult(
                isSuccessful = false,
                errorMessage = context.getString(R.string.password_do_not_match),
                isToast = true
            )
        }

        else -> {
            ValidationResult(
                isSuccessful = true
            )
        }
    }
}