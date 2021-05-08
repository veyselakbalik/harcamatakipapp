package com.veyselakbalik.harcamatakipuygulamasi.repository

import androidx.lifecycle.LiveData
import com.veyselakbalik.harcamatakipuygulamasi.data.HarcamalarDao
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar

class HarcamalarRepository(private val harcamalarDao: HarcamalarDao) {

    val readAllData: LiveData<List<Harcamalar>> = harcamalarDao.readAllData()

    suspend fun harcamaEkle(harcamalar: Harcamalar){
        harcamalarDao.harcamaEkle(harcamalar)
    }

    suspend fun harcamayiSil(harcamalar: Harcamalar){
        harcamalarDao.harcamayiSil(harcamalar)
    }
    suspend fun tumHarcamalariSil(){
        harcamalarDao.tumHarcamalariSil()
    }
    suspend fun harcamayiGuncelle(harcamalar: Harcamalar){
        harcamalarDao.harcamayiGuncelle(harcamalar)
    }



}