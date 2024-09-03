package com.dev.intourist.presentation.ui.model.validation

import com.dev.intourist.presentation.ui.model.validation.ValidationResult

interface Validator {
    operator fun invoke(text: String): ValidationResult
}