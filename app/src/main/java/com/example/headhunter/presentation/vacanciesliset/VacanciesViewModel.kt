package com.example.headhunter.presentation.vacanciesliset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.Items
import com.example.domain.reps.NetworkRep
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacanciesViewModel(
    private val networkRep: NetworkRep
) : ViewModel() {
    fun createFlow(params: PagerDataParamsParcel): Flow<PagingData<Items>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { VacanciesPagingSource(
                networkRep,
                params
            )
        }
    ).flow.cachedIn(viewModelScope)

    class Factory @Inject constructor(
        private val networkRep: NetworkRep
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VacanciesViewModel(networkRep) as T
        }
    }
}