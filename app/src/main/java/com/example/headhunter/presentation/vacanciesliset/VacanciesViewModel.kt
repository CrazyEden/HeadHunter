package com.example.headhunter.presentation.vacanciesliset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.Items
import com.example.domain.usecase.GetPageVacanciesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacanciesViewModel(
    private val getPageVacanciesUseCase: GetPageVacanciesUseCase,
) : ViewModel() {
    fun createFlow(params: PagerDataParamsParcel): Flow<PagingData<Items>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { VacanciesPagingSource(
                getPageVacanciesUseCase,
                params
            )
        }
    ).flow.cachedIn(viewModelScope)

    class Factory @Inject constructor(
        private val getPageVacanciesUseCase: GetPageVacanciesUseCase,
    ):ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(VacanciesViewModel::class.java))
            return VacanciesViewModel(
                getPageVacanciesUseCase = getPageVacanciesUseCase,
            ) as T
        }
    }
}