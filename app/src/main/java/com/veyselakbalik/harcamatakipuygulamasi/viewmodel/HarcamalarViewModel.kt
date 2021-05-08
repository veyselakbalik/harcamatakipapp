package com.veyselakbalik.harcamatakipuygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.veyselakbalik.harcamatakipuygulamasi.data.HarcamalarDatabase
import com.veyselakbalik.harcamatakipuygulamasi.repository.HarcamalarRepository
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HarcamalarViewModel(application: Application): AndroidViewModel(application) {
    val readAllData : LiveData<List<Harcamalar>>
    private val repository: HarcamalarRepository

    init {
        val harcamalarDao = HarcamalarDatabase.getDatabase(application).harcamalarDao()
        repository = HarcamalarRepository(harcamalarDao)
        readAllData = repository.readAllData
    }

    fun harcamaEkle(harcamalar: Harcamalar) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.harcamaEkle(harcamalar)
        }
    }
    fun harcamayiSil(harcamalar: Harcamalar){
        viewModelScope.launch(Dispatchers.IO){
            repository.harcamayiSil(harcamalar)
        }
    }
    fun tumHarcamalariSil(){
        viewModelScope.launch(Dispatchers.IO){
            repository.tumHarcamalariSil()
        }
    }
    fun harcamayiGuncelle(harcamalar: Harcamalar){
        viewModelScope.launch(Dispatchers.IO){
            repository.harcamayiGuncelle(harcamalar)
        }
    }

}