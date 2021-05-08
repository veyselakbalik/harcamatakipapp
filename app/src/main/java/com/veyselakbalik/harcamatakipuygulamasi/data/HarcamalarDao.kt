package com.veyselakbalik.harcamatakipuygulamasi.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar

@Dao
interface HarcamalarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun harcamaEkle(harcama: Harcamalar)

    @Update
    suspend fun harcamayiGuncelle(harcama: Harcamalar)

    @Delete
    suspend fun harcamayiSil(harcama: Harcamalar)

    @Query("DELETE FROM harcama_tablosu")
    suspend fun tumHarcamalariSil()

    @Query("SELECT * FROM harcama_tablosu ORDER BY id ASC")
    fun readAllData(): LiveData<List<Harcamalar>>

}