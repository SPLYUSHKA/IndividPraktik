package com.example.var7individpraktik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class MainActivity2 : AppCompatActivity(),FilmListFragment.Callbacks{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val currentFrgament =supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(currentFrgament == null){
            val fragment = FilmListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }
    }
    override fun onCrimeSelected(filmId: UUID) {
        val fragment = FilmFragment.newInstance(filmId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,
                fragment)
            .addToBackStack(null)
            .commit()
    }
}