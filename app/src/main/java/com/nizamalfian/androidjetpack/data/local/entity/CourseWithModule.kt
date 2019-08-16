package com.nizamalfian.androidjetpack.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by nizamalfian on 25/07/2019.
 */
data class CourseWithModule(
    @Embedded var course: CourseEntity?=null,
    @Relation(parentColumn = "courseId",entityColumn = "courseId")
    var modules:List<ModuleEntity> = ArrayList()
)