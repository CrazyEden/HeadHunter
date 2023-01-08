package com.example.headhunter.presentation.vacancyinfo

import androidx.lifecycle.*
import com.example.domain.model.RoomDataEntity
import com.example.domain.model.vacancyinfo.VacancyInfo
import com.example.domain.usecase.GetVacancyInfoUseCase
import com.example.domain.usecase.localdata.GetListIdUseCase
import com.example.domain.usecase.localdata.RemoveVacancyUseCase
import com.example.domain.usecase.localdata.SaveVacancyUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class VacancyInfoViewModel(
    private val id:String?,
    private val getVacancyInfoUseCase: GetVacancyInfoUseCase,
    private var saveVacancyUseCase: SaveVacancyUseCase,
    private var removeVacancyUseCase: RemoveVacancyUseCase,
    private var getListIdUseCase: GetListIdUseCase
) : ViewModel() {
    private val _vacancyInfoLiveData = MutableLiveData<VacancyInfo?>()
    val vacancyInfoLiveData:LiveData<VacancyInfo?> = _vacancyInfoLiveData

    private val _isVacancyFavoriteLiveData = MutableLiveData<Boolean>()
    val isVacancyFavoriteLiveData:LiveData<Boolean> = _isVacancyFavoriteLiveData
    init {
        viewModelScope.launch() {
            _isVacancyFavoriteLiveData.postValue(
                getListIdUseCase.execute()?.contains(id) ?: false
            )
        }
    }

    fun loadVacancy() {
        if(id==null) return
        viewModelScope.launch {
            runCatching {
                _vacancyInfoLiveData.postValue(
                    getVacancyInfoUseCase.execute(id)
                )
            }.getOrElse {  _vacancyInfoLiveData.postValue(null) }
        }
    }

    fun makeVacancyFavorite(roomData: RoomDataEntity){
        viewModelScope.launch {
            saveVacancyUseCase.execute(roomData)
        }
    }

    fun removeVacancyFromFavoriteList(roomData: RoomDataEntity){
        viewModelScope.launch {
            removeVacancyUseCase.execute(roomData)
        }
    }


    class Factory @AssistedInject constructor(
        @Assisted(VACANCY_ID_KEY) private val id:String?,
        private val getVacancyInfoUseCase: GetVacancyInfoUseCase,
        private var saveVacancyUseCase: SaveVacancyUseCase,
        private var removeVacancyUseCase: RemoveVacancyUseCase,
        private var getListIdUseCase: GetListIdUseCase
    ) : ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(VacancyInfoViewModel::class.java))
            return VacancyInfoViewModel(
                id = id,
                getVacancyInfoUseCase = getVacancyInfoUseCase,
                saveVacancyUseCase = saveVacancyUseCase,
                removeVacancyUseCase = removeVacancyUseCase,
                getListIdUseCase = getListIdUseCase
            ) as T
        }
        @AssistedFactory
        interface Factoryy{
            fun create (@Assisted(VACANCY_ID_KEY) id:String?):Factory
        }
    }

    companion object{
        private const val VACANCY_ID_KEY = "vacancyId"
    }
}