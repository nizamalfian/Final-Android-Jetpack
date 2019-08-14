package com.nizamalfian.androidjetpack.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Created by nizamalfian on 03/07/2019.
 */
@Entity(
    primaryKeys = ["genreId", "movieId"],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = arrayOf("movieId"),
        childColumns = arrayOf("movieId")
    )],
    indices = [Index(
        value = ["genreId","movieId"]
    )]
)
data class GenreEntity(var genreId:Int,
                       var movieId:Int,
                       val name:String)