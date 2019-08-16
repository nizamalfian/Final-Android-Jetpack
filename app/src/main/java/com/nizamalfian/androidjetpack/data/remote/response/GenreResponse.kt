package com.nizamalfian.androidjetpack.data.remote.response

import androidx.room.PrimaryKey

/**
 * Created by nizamalfian on 03/07/2019.
 */
data class GenreResponse(@PrimaryKey var id:Int,
                         val name:String)