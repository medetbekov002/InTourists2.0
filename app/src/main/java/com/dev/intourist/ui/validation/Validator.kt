package com.dev.intourist.ui.validation

import com.dev.intourist.ui.validation.ValidationResult

interface Validator {
    operator fun invoke(text: String): ValidationResult
}