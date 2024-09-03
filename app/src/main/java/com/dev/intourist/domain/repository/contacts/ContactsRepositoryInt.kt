package com.dev.intourist.domain.repository.contacts

import com.dev.intourist.common.UIState
import com.dev.intourist.data.remote.model.contacts.ContactModel
import kotlinx.coroutines.flow.StateFlow

interface ContactsRepositoryInt {
    suspend fun getContacts(): StateFlow<UIState<ContactModel>>
}