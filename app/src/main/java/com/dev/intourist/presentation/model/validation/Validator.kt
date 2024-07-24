package com.dev.intourist.presentation.model.validation

import com.dev.intourist.presentation.model.validation.ValidationResult

interface Validator {
    operator fun invoke(text: String): ValidationResult
}