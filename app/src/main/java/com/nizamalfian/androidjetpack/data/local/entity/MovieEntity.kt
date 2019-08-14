package com.nizamalfian.androidjetpack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by nizamalfian on 03/08/2019.
 */
@Entity
data class MovieEntity(@PrimaryKey var movieId:Int,
                       var title:String,
                       var originalTitle:String,
                       var posterPath:String,
                       var voteCount:Int,
                       var voteAverage:Double,
                       var popularity:Double,
                       var backdropPath:String,
                       var overview:String,
                       var releaseDate:String,
                       var language:String,
                       var isTVShow:Boolean=false,
                       var isBookmarked:Boolean=false)