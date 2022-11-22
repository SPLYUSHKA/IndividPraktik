package com.example.var7individpraktik

import androidx.lifecycle.ViewModel

class FilmListViewModel : ViewModel() {
   // val filmes = mutableListOf<Film>()
 //   var images = listOf(R.drawable.film1,R.drawable.film2,R.drawable.film3,R.drawable.film4,R.drawable.film5)
       //  init {
        //for (i in 0 until 5) {
         //   val film = Film()
          //  film.Title = "фильм${i}"
           // film.Author ="автор${i}"
        //    film.Genre= "жанр${i}"
         //   film.Image = images[i]
          //  filmes.add(film)
       // }
    //}


private val filmRepository = FilmRepository.get()
val filmListLiveData =filmRepository.getFilms()
fun deleteFilm(film :Film){
    filmRepository.deleteFilm(film)
}
fun addFilm(film :Film){
    filmRepository.addFilm(film) }

}
