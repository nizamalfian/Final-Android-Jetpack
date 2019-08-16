package com.nizamalfian.androidjetpack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by L
 *
 * on 6/26/2019
 */
@Entity
data class CourseEntity(
    @PrimaryKey var courseId:String="",
    var title:String="",
    var description:String="",
    var deadline:String="",
    var imagePath:String="",
    var isBookmarked:Boolean=false
)