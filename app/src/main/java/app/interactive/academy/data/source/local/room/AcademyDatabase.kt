package app.interactive.academy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity

/**
 * Created by nizamalfian on 26/07/2019.
 */
@Database(
    entities = [CourseEntity::class,ModuleEntity::class],
    version = 1,
    exportSchema = false)
abstract class AcademyDatabase : RoomDatabase(){
    abstract fun academyDao():AcademyDao
    companion object{
        private var INSTANCE:AcademyDatabase?=null
        private val sLock = Any()
        fun getInstance(context:Context):AcademyDatabase{
            synchronized(sLock){
                if(INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.applicationContext,
                        AcademyDatabase::class.java,"Academies.db")
                        .build()
                }
                return INSTANCE as AcademyDatabase
            }
        }
    }
}