package com.nizamalfian.androidjetpack.data.remote.response

/**
 * Created by L
 *
 * on 7/3/2019
 */
data class MovieResponse(val id:Int,
                         val title:String,
                         val originalTitle:String,
                         val posterPath:String,
                         val voteCount:Int,
                         val voteAverage:Double,
                         val popularity:Double,
                         val backdropPath:String,
                         val overview:String,
                         val releaseDate:String,
                         val language:String,
                         val genre_ids:List<Int>,
                         val isTVShow:Boolean=false,
                         val isAdult:Boolean=false)