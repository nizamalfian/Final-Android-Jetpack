package app.interactive.academy.data

/**
 * Created by L
 *
 * on 6/26/2019
 */
data class ModuleEntity(
    val moduleId:String,
    val courseId:String,
    val title:String,
    val position: Int,
    val contentEntity: ContentEntity?=null,
    val read:Boolean=false
)