package com.example.desafiohavan.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.desafiohavan.domain.entities.ShoppingItem

@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract val shoppingDao: ShoppingDao

    companion object{

        private var INSTANCE: ShoppingDatabase? = null

        fun getDatabase(context: Context): ShoppingDatabase{
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "shopping_database"
                )
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }

}