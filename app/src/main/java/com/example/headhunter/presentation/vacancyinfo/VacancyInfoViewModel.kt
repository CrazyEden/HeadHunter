package com.example.headhunter.presentation.vacancyinfo

import androidx.lifecycle.*
import com.example.domain.usecase.GetVacancyInfoUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VacancyInfoViewModel(
    private val id:String,
    private val getVacancyInfoUseCase: GetVacancyInfoUseCase
) : ViewModel() {
    private val _vacancyInfoLiveData = MutableLiveData<com.example.domain.model.vacancyinfo.VacancyInfo>()
    val vacancyInfoLiveData:LiveData<com.example.domain.model.vacancyinfo.VacancyInfo> = _vacancyInfoLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _vacancyInfoLiveData.postValue(getVacancyInfoUseCase.execute(id))
        }
    }

    class Factory @AssistedInject constructor(
        @Assisted(VACANCY_ID_KEY) private val id:String,
        private val getVacancyInfoUseCase: GetVacancyInfoUseCase
    ) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(VacancyInfoViewModel::class.java))
            return VacancyInfoViewModel(id,getVacancyInfoUseCase) as T
        }
        @AssistedFactory
        interface Factoryy{
            fun create (@Assisted(VACANCY_ID_KEY) id:String):Factory
        }
    }

    companion object{
        private const val VACANCY_ID_KEY = "vacancyId"
    }
}