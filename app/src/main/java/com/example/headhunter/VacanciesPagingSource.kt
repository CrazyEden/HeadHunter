package com.example.headhunter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.headhunter.model.data.Items
import com.example.headhunter.model.reps.NetworkRep

class VacanciesPagingSource(
    private val networkRep: NetworkRep
) : PagingSource<Int, Items>() {

    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        val key = params.key ?: 0
        try {
            val page = networkRep.getVacancies(key)!!
            val pKey = if (key == 0) null else key.minus(1)
            val nKey = if (key == page.pages) null else key.plus(1)
            return LoadResult.Page(
                data = page.items,
                prevKey = pKey,
                nextKey = nKey
            )
        }catch (e:Exception){
            Log.w(TAG, "load: ", e)
            return LoadResult.Error(e)
        }
    }
}