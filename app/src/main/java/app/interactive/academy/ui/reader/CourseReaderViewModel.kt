package app.interactive.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.utils.generateDummyModules

/**
 * Created by L
 *
 * on 6/29/2019
 */
class CourseReaderViewModel(private val academyRepository: AcademyRepository):ViewModel(){
    var courseId: String? = null
    var moduleId: String? = null

    /*fun getModules(): ArrayList<ModuleEntity>? = generateDummyModules(courseId ?: "")

    fun getSelectedModule(): ModuleEntity? {
        getModules()?.forEach {
            if (it.moduleId == moduleId)
                return it.copy(contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">${it.title}</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"))
        }
        return null
    }*/

    fun getModules(): LiveData<ArrayList<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId?:"")

    fun getSelectedModule(): LiveData<ModuleEntity> = academyRepository.getContent(courseId?:"",moduleId?:"")
}