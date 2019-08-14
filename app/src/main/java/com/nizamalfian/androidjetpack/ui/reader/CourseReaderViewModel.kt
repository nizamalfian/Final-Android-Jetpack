package com.nizamalfian.androidjetpack.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.ModuleEntity
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 6/29/2019
 */
class CourseReaderViewModel(private val academyRepository: MovieRepository):ViewModel(){
    private val courseId = MutableLiveData<String>()
    private val moduleId = MutableLiveData<String>()
    val modules : LiveData<Resource<List<ModuleEntity>>> = Transformations.switchMap(courseId){ courseId->academyRepository.getAllModulesByCourse(courseId)}
    val selectedModule= Transformations.switchMap(moduleId){ selectedPosition->academyRepository.getContent(selectedPosition)}

    fun setCourseId(courseId:String){
        this.courseId.value=courseId
    }

    fun getCourseId():String?= courseId.value

    fun setSelectedModule(moduleId:String){
        this.moduleId.value=moduleId
    }

    fun readContent(module: ModuleEntity){
        academyRepository.setReadModule(module)
    }

    fun getModulesSize():Int{
        if(modules.value!=null){
            val moduleEntities:List<ModuleEntity>? = modules.value?.data
            if(moduleEntities!=null){
                return moduleEntities.size
            }
        }
        return 0
    }

    fun setNextPage(){
        if(selectedModule.value!=null && modules.value!=null){
            val moduleEntity: ModuleEntity?=selectedModule.value?.data
            val moduleEntities:List<ModuleEntity>? = modules.value?.data
            if(moduleEntity!=null && moduleEntities!=null){
                val position = moduleEntity.position
                if(position<moduleEntities.size && position >=0)
                    setSelectedModule(moduleEntities[position+1].moduleId)
            }
        }
    }

    fun setPrevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity: ModuleEntity?=selectedModule.value?.data
            val moduleEntities:List<ModuleEntity>? = modules.value?.data
            if(moduleEntity!=null && moduleEntities!=null) {
                val position = moduleEntity.position
                if(position<moduleEntities.size && position>=0)
                    setSelectedModule(moduleEntities[position-1].moduleId)
            }
        }
    }
}