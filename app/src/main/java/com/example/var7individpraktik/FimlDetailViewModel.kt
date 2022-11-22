package com.example.var7individpraktik

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class FilmDetailViewModel() : ViewModel() {

    private val filmRepository = FilmRepository.get()
    private val filmIdLiveData = MutableLiveData<UUID>()

    var filmLiveData: LiveData<Film?> =
        Transformations.switchMap(filmIdLiveData) { filmId -> filmRepository.getFilm(filmId)
        }

    fun loadCrime(filmId: UUID) {
        filmIdLiveData.value = filmId
    }
    fun saveCrime(film: Film) {
        filmRepository.updateFilm(film)
    }
}