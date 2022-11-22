package com.example.var7individpraktik

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.util.*
private const val ARG_FILM_ID = "film_id"
private const val TAG = "FilmFragment"
class FilmFragment : Fragment() {
    private lateinit var film: Film
    private lateinit var _Titlefilm : EditText
    private lateinit var  _Authorfilm :EditText
    private lateinit var  _genre :EditText
    private lateinit var _imageFilm : ImageView
    var flag : Boolean = false
    var images = listOf(R.drawable.film1,R.drawable.film2,R.drawable.film3,R.drawable.film4,R.drawable.film5)
    private val filmDetailViewModel: FilmDetailViewModel by lazy {
        ViewModelProviders.of(this).get(FilmDetailViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film = Film()
        film.Image = images[rand(0,4)]
        val filmId: UUID = arguments?.getSerializable(ARG_FILM_ID) as UUID
        filmDetailViewModel.loadCrime(filmId)
    }
    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view,
            savedInstanceState)
        filmDetailViewModel.filmLiveData.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { film ->
                film?.let {
                    this.film = film

                    updateUI()
                }
            })
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film,container,false)
        _Titlefilm =view.findViewById(R.id.nameFilm) as EditText
        _Authorfilm =view.findViewById(R.id.author) as EditText
        _genre =view.findViewById(R.id.GenreFilm) as EditText
        _imageFilm =view.findViewById(R.id.iamge1) as ImageView
       return view
    }
    override fun onStop() {
        super.onStop()
        if(flag == false){ flag =true
            film.Image=images[rand(0,4)]}
        filmDetailViewModel.saveCrime(film)
    }
    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher
        {
        override fun beforeTextChanged(
            sequence: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }
        override fun onTextChanged(
            sequence: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            film.Title =
                sequence.toString()
        }
        override fun
                afterTextChanged(sequence: Editable?) {

        }


    }
        val titleWatcher2 = object : TextWatcher
        {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                film.Author =
                    sequence.toString()
            }
            override fun
                    afterTextChanged(sequence: Editable?) {

            }


        }
        val titleWatcher3 = object : TextWatcher
        {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                film.Genre =
                    sequence.toString()
            }
            override fun
                    afterTextChanged(sequence: Editable?) {

            }


        }
      _Titlefilm.addTextChangedListener(titleWatcher)
      _Authorfilm.addTextChangedListener(titleWatcher2)
        _genre.addTextChangedListener(titleWatcher3)

    }
    private fun updateUI() {
        _Titlefilm.setText(film.Title)
        _Authorfilm.setText(film.Author)
        _genre.setText(film.Genre)

        if(flag == true) {_imageFilm.setImageResource(film.Image!!)}
        else {_imageFilm.setImageResource(images[rand(0,4)])}



    }
    companion object {

        fun newInstance(filmId: UUID): FilmFragment {
            val args = Bundle().apply {
                putSerializable(ARG_FILM_ID, filmId)
            }
            return FilmFragment().apply {
                arguments = args
            }
        }
    }
    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}
