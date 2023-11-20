package com.mrntlu.pagination.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrntlu.pagination.models.organizations.Organization
import com.mrntlu.pagination.repository.AssociationManuelPagingRepository
import com.mrntlu.pagination.utils.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssociationManuelPagingViewModel @Inject constructor(
    private val repository: AssociationManuelPagingRepository,
) : ViewModel() {
    val newsList = mutableStateListOf<Organization>()

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        getAssociation()
    }

    fun getAssociation() = viewModelScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
            if (page == 1) {
                listState = ListState.LOADING
            } else {
                listState = ListState.PAGINATING
                delay(5000)
            }

            repository.getAssociation(page).collect() {
                if (it.status == "ok") {
                    canPaginate = it.organizations?.size == 25

                    if (page == 1) {
                        newsList.clear()
                        it.organizations?.let { organization -> newsList.addAll(organization) }
                    } else {
                        it.organizations?.let { organization -> newsList.addAll(organization) }
                    }

                    listState = ListState.IDLE

                    if (canPaginate)
                        page++
                } else {
                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                }
            }
        }
    }

    override fun onCleared() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
        super.onCleared()
    }
}