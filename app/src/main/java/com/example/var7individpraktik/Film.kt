package com.example.var7individpraktik

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Film(@PrimaryKey val id :UUID = UUID.randomUUID(),
    var Title: String? = null,
    var Author: String? = null,
    var Genre: String? = null,
    var Image:Int? = null
){}
