package com.example.headhunter.presentation.vacancyinfo

import androidx.lifecycle.*
import com.example.domain.reps.NetworkRep
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VacancyInfoViewModel(
    private val id:String,
    private val networkRep: NetworkRep
) : ViewModel() {
    private val _vacancyInfoLiveData = MutableLiveData<com.example.domain.model.vacancyinfo.VacancyInfo>()
    val vacancyInfoLiveData:LiveData<com.example.domain.model.vacancyinfo.VacancyInfo> = _vacancyInfoLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _vacancyInfoLiveData.postValue(networkRep.getVacancyInfo(id))
        }
    }

    class Factory @AssistedInject constructor(
        @Assisted(VACANCY_ID_KEY) private val id:String,
        private val networkRep: NetworkRep
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VacancyInfoViewModel(id,networkRep) as T
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