package app.interactive.academy.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Created by L
 *
 * on 6/26/2019
 */
@Entity(
    primaryKeys = ["moduleId", "courseId"],
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = arrayOf("courseId"),
        childColumns = arrayOf("courseId")
    )],
    indices = [Index(
        value = ["moduleId","courseId"]
    )]
)
data class ModuleEntity(
    var moduleId: String="",
    var courseId: String="",
    var title: String="",
    var position: Int=0,
    @Embedded var contentEntity: ContentEntity? = null,
    var isRead: Boolean = false
)