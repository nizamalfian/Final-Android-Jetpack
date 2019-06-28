package app.interactive.academy.data

/**
 * Created by L
 *
 * on 6/26/2019
 */
data class CourseEntity(
    val courseId:String,
    val title:String,
    val description:String,
    val deadline:String,
    val imagePath:String,
    val bookmarked:Boolean?=null
)