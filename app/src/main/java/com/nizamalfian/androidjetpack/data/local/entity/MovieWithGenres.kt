package com.nizamalfian.androidjetpack.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by nizamalfian on 03/08/2019.
 */
data class MovieWithGenres(
    @Embedded var movie: MovieEntity,
    @Relation(parentColumn = "movieId",entityColumn = "movieId") var genres:List<GenreEntity>
)