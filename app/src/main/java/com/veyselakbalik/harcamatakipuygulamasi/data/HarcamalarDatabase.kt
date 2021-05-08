package com.veyselakbalik.harcamatakipuygulamasi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.veyselakbalik.harcamatakipuygulamasi.model.Harcamalar

@Database(entities = [Harcamalar::class],version = 1,exportSchema = false)
abstract class HarcamalarDatabase : RoomDatabase() {

    abstract fun harcamalarDao() : HarcamalarDao

    companion object{
        @Volatile
        private var INSTANCE: HarcamalarDatabase? = null

        fun getDatabase(context: Context):HarcamalarDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HarcamalarDatabase::class.java,
                    "harcamalar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}