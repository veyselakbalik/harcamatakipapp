package com.veyselakbalik.harcamatakipuygulamasi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {
    private var _name = MutableLiveData("İsminiz")
    private var _cinsiyet = MutableLiveData("Bey")
    private var _para = MutableLiveData("100 TL")
    private var _miktar = MutableLiveData(0)
    val name : LiveData<String> = _name
    val cinsiyet : LiveData<String> = _cinsiyet
    val para : LiveData<String> = _para
    val miktar : LiveData<Int> = _miktar
    fun saveName(newName:String){
        _name.value = newName
    }
    fun saveSex(newSex:String){
        _cinsiyet.value = newSex
    }
    fun saveMoney(newMoney:String){
        _para.value = newMoney
    }
    fun saveMiktar(newMiktar:Int){
        miktar.value
    }
}