package com.nizamalfian.androidjetpack.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nizamalfian.androidjetpack.data.local.entity.CourseEntity
import com.nizamalfian.androidjetpack.data.local.entity.GenreEntity
import com.nizamalfian.androidjetpack.data.local.entity.ModuleEntity
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity

/**
 * Created by nizamalfian on 26/07/2019.
 */
@Database(
    entities = [CourseEntity::class, ModuleEntity::class, MovieEntity::class, GenreEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun academyDao(): MovieDao
    companion object{
        @Volatile private var INSTANCE: MovieDatabase?=null
        fun getInstance(context:Context): MovieDatabase {
            synchronized(MovieDatabase::class.java){
                if(INSTANCE ==null){
                    INSTANCE =Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java,"Movies.db")
                        .build()
                }
                return INSTANCE as MovieDatabase
            }
        }
    }
}