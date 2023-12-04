package com.example.kochideengenerator.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Idea(
    val title:String,
    val image:String,
    val type:String,
    @PrimaryKey val id:Int? = null

)
