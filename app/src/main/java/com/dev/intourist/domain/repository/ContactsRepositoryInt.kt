package com.dev.intourist.domain.repository

import androidx.lifecycle.LiveData
import com.dev.intourist.common.UIState
import com.dev.intourist.data.remote.dtos.contacts.ContactModel

interface ContactsRepositoryInt {
    suspend fun getContacts(): LiveData<UIState<ContactModel>>
}