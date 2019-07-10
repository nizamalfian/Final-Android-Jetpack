package app.interactive.academy.data.source.remote

import android.os.Handler
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse

/**
 * Created by L
 *
 * on 7/8/2019
 */
class RemoteRepository(private val jsonHelper: JSONHelper){
    private val SERVICE_LATENCY_IN_MILLIS: Long=2000

    companion object{
        private var INSTANCE:RemoteRepository?=null
        fun getInstance(jsonHelper: JSONHelper):RemoteRepository{
            if(INSTANCE==null)
                INSTANCE=RemoteRepository(jsonHelper)
            return INSTANCE as RemoteRepository
        }
    }

    /*fun getAllCourses(callback: LoadCoursesCallback) {
        val handler = Handler()
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }*/

    fun getAllCourses():List<CourseResponse> = jsonHelper.loadCourses()

    fun getAllModules(courseId:String):List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId:String):ContentResponse? = jsonHelper.loadContent(moduleId)
}