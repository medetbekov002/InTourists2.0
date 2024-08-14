package com.dev.intourist.domain.usecase

import androidx.lifecycle.LiveData
import com.dev.intourist.common.UIState
import com.dev.intourist.data.remote.dtos.contacts.ContactModel
import com.dev.intourist.domain.repository.ContactsRepositoryInt

class ContactsUseCase(private val repository: ContactsRepositoryInt) {
    suspend fun getContacts() = repository.getContacts()
}