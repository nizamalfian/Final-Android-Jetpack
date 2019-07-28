package app.interactive.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.room.Transaction
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class AcademyViewModel(private val academyRepository: AcademyRepository):ViewModel() {
    private val login = MutableLiveData<String>()

    fun setUsername(username:String){
        login.value=username
    }

    fun getCourses():LiveData<Resource<List<CourseEntity>>> = Transformations.switchMap(login){data->academyRepository.getAllCourses()}
}