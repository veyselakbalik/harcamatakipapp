package com.veyselakbalik.harcamatakipuygulamasi.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "harcama_tablosu")
data class Harcamalar(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val urunAdi : String,
        val urunHarcamaMiktari : Int,
        val urunTipi : String,
        val urunParaBirimi : String,

):Parcelable