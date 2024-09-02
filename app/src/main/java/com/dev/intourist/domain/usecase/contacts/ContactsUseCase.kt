package com.dev.intourist.domain.usecase.contacts

//import androidx.lifecycle.LiveData
import com.dev.intourist.domain.repository.contacts.ContactsRepositoryInt

class ContactsUseCase(private val repository: ContactsRepositoryInt) {
    suspend fun getContacts() = repository.getContacts()
}