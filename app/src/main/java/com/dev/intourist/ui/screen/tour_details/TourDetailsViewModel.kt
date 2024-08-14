package com.dev.intourist.ui.screen.tour_detailsimport android.util.Logimport androidx.lifecycle.viewModelScopeimport com.dev.intourist.common.UIStateimport com.dev.intourist.data.remote.dtos.contacts.ContactModelimport com.dev.intourist.data.remote.dtos.tours.ToursModelimport com.dev.intourist.domain.usecase.ContactsUseCaseimport com.dev.intourist.domain.usecase.TourUseCaseimport com.dev.intourist.presentation.base.viewmodel.BaseViewModelimport kotlinx.coroutines.flow.MutableStateFlowimport kotlinx.coroutines.flow.asStateFlowimport kotlinx.coroutines.launchclass TourDetailsViewModel(    private val useCase: TourUseCase,    private val contactUseCase: ContactsUseCase) : BaseViewModel() {    private val _tour = MutableStateFlow<UIState<ToursModel.Result>>(UIState.Idle())    val tour = _tour.asStateFlow()    private val _tours = MutableStateFlow<UIState<ToursModel>>(UIState.Idle())    val tours = _tours.asStateFlow()    private val _contacts = MutableStateFlow<UIState<ContactModel>>(UIState.Idle())    val contacts = _contacts.asStateFlow()    fun getTourById(id: Int) {        viewModelScope.launch {            useCase.getTourById(id).collect {                _tour.value = it            }        }    }    fun getAllTours(pageSize: Int) {        viewModelScope.launch {            useCase.getAllTours(pageSize = pageSize).collect {                _tours.value = it            }        }    }    fun getContacts() {        viewModelScope.launch {            contactUseCase.getContacts().collect {                _contacts.value = it            }        }    }}