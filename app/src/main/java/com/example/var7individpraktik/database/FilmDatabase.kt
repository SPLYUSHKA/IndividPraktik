package com.example.var7individpraktik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.var7individpraktik.Film

@Database(entities = [Film::class ], version=1)
@TypeConverters(FilmTypeConverters ::class)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmDao
}