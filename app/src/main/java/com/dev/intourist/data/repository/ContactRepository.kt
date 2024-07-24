package com.dev.intourist.data.repository

import androidx.lifecycle.LiveData
import com.dev.intourist.common.UIState
import com.dev.intourist.data.base.BaseRepository
import com.dev.intourist.data.remote.dtos.contacts.ContactModel
import com.dev.intourist.data.remote.service.ApiService
import com.dev.intourist.domain.repository.ContactsRepositoryInt

class ContactRepository(private val apiService: ApiService) : BaseRepository(), ContactsRepositoryInt {
    override suspend fun getContacts(): LiveData<UIState<ContactModel>> =
        performRequest {
            apiService.getContacts()
        }
}