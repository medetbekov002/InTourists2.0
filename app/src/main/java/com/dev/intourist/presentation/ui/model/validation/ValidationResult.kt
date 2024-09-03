package com.dev.intourist.presentation.ui.model.validation

class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: String = "",
    val isToast: Boolean = false,
)