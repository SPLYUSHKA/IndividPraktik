package com.example.var7individpraktik.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.var7individpraktik.Film
import java.util.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM film")
    fun getFilms(): LiveData<List<Film>>
    @Query("SELECT * FROM film WHERE id= :id")
    fun getFilm(id: UUID): LiveData<Film?>
    @Update
    fun updateFilm(film: Film)
    @Insert
    fun addFilm(film: Film)
    @Delete
    fun deleteFilm(film: Film)
}
