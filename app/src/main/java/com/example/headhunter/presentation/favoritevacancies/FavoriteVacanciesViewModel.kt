package com.example.headhunter.presentation.favoritevacancies

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.model.RoomDataEntity
import com.example.domain.usecase.localdata.GetAllVacanciesUseCase
import com.example.headhunter.presentation.vacanciesliset.TAG
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteVacanciesViewModel(
    private val getAllVacanciesUseCase: GetAllVacanciesUseCase,
) : ViewModel() {

    private val _vacanciesLiveData = MutableLiveData<MutableList<RoomDataEntity>?>()
    val vacanciesLiveData: LiveData<MutableList<RoomDataEntity>?> = _vacanciesLiveData


    fun updateUi() {
        viewModelScope.launch {
            _vacanciesLiveData.postValue(getAllVacanciesUseCase.execute())
        }
    }

    override fun onCleared() {
        Log.w(TAG, "onCleared: ")
        super.onCleared()
    }
    class Factory @Inject constructor(
        private val getAllVacanciesUseCase: GetAllVacanciesUseCase,
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(FavoriteVacanciesViewModel::class.java))
            return FavoriteVacanciesViewModel(getAllVacanciesUseCase) as T
        }
    }
}