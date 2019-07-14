package app.interactive.academy.data.source.remote

import android.os.Handler
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse
import app.interactive.academy.utils.EspressoIdlingResource


/**
 * Created by L
 *
 * on 7/8/2019
 */
class RemoteRepository(private val jsonHelper: JSONHelper) {
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    companion object {
        private var INSTANCE: RemoteRepository? = null
        fun getInstance(jsonHelper: JSONHelper): RemoteRepository {
            if (INSTANCE == null)
                INSTANCE = RemoteRepository(jsonHelper)
            return INSTANCE as RemoteRepository
        }
    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId:String,callback:GetContentCallback){
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId) as ContentResponse)
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()

    fun getAllModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse? = jsonHelper.loadContent(moduleId)

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)

        fun onDataNotAvailable()
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)

        fun onDataNotAvailable()
    }

    interface GetContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)

        fun onDataNotAvailable()
    }
}