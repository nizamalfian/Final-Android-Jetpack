package app.interactive.academy.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllCoursesAsLiveData():LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<CourseResponse>>>()
        Handler().postDelayed({
            resultCourse.value=ApiResponse.success(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultCourse
    }

    fun getAllModulesByCourseAsLiveData(courseId: String):LiveData<ApiResponse<List<ModuleResponse>>>{
        EspressoIdlingResource.increment()
        val resultModules = MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        Handler().postDelayed({
            resultModules.value=ApiResponse.success(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
        return resultModules
    }

    fun getContentAsLiveData(moduleId:String):LiveData<ApiResponse<ContentResponse?>>{
        EspressoIdlingResource.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse?>>()
        Handler().postDelayed({
            resultContent.value= ApiResponse.success(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
        return resultContent
    }

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