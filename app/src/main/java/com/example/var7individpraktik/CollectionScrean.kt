package com.example.var7individpraktik

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class CollectionScrean : AppCompatActivity() {
   private lateinit var like : ImageView
   private lateinit var time : ImageView
   private lateinit var Gw  : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_screan)
        like = findViewById(R.id.likecolection)
        time =findViewById(R.id.timecolection)
        Gw = findViewById(R.id.Gowalkcolection)
        like.setOnClickListener {
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        time.setOnClickListener {
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        Gw.setOnClickListener {
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }


}