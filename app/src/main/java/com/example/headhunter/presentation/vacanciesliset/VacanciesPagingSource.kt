package com.example.headhunter.presentation.vacanciesliset

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.model.PagerDataParamsParcel
import com.example.domain.model.pagerdata.Items
import com.example.domain.usecase.GetPageVacanciesUseCase

class VacanciesPagingSource(
    private val getPageVacanciesUseCase: GetPageVacanciesUseCase,
    private val requestParams: PagerDataParamsParcel
) : PagingSource<Int, Items>() {

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        val key = params.key ?: 0

        return try {
            val page = getPageVacanciesUseCase.execute(
                page =key,
                params = requestParams.toPagerDataParams()
            )!!
            val pKey = if (key == 0) null else key.minus(1)
            val nKey = if (key == page.pages) null else key.plus(1)
            LoadResult.Page(
                data = page.items,
                prevKey = pKey,
                nextKey = nKey
            )
        }catch (e:Throwable){
            Log.wtf(TAG, "load: ", e)
            LoadResult.Error(e)
        }
    }
}