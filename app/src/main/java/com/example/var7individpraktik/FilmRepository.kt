package com.example.var7individpraktik

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.var7individpraktik.database.FilmDatabase
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "filmdatabase"
class FilmRepository private constructor(context: Context) {

    private val database : FilmDatabase =
        Room.databaseBuilder(context.applicationContext,
            FilmDatabase::class.java,
            DATABASE_NAME
        ).build()

    private val filmDao = database.filmeDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getFilms(): LiveData<List<Film>> = filmDao.getFilms()

    fun getFilm(id: UUID): LiveData<Film?> = filmDao.getFilm(id)
    fun updateFilm(film: Film) {
        executor.execute {
            filmDao.updateFilm(film)
        }
    }
    fun addFilm(film: Film) {
        executor.execute {
            filmDao.addFilm(film)
        }
    }
    fun deleteFilm(film: Film)
    {
        executor.execute {
            filmDao.deleteFilm(film)
        }
    }
    companion object {
        private var INSTANCE: FilmRepository? =
            null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FilmRepository(context)
            }
        }
        fun get(): FilmRepository {
            return INSTANCE ?:
            throw
            IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
