package app.interactive.academy.ui.reader

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.data.source.vo.Status
import app.interactive.academy.ui.detail.CourseReaderCallback
import app.interactive.academy.ui.reader.content.ModuleContentFragment
import app.interactive.academy.ui.reader.list.ModuleListFragment
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.data.source.vo.Status.*

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    private val initObserver: Observer<Resource<List<ModuleEntity>>> = Observer {modules->
        modules?.let{
            when(it.status){
                LOADING -> {}
                SUCCESS -> {
                    it.data?.let{data->
                        if(data.isNotEmpty()){
                            val firstModule=data[0]
                            val isFirstModuleRead=firstModule.isRead
                            if(!isFirstModuleRead){
                                viewModel.setSelectedModule(firstModule.moduleId)
                            }else{
                                getLastReadFromModules(data)?.let{moduleId->
                                    viewModel.setSelectedModule(moduleId)
                                }
                            }
                        }
                    }
                }
                ERROR->{
                    Toast.makeText(this, "Gagal menampilkan data.", Toast.LENGTH_SHORT).show()
                    removeObserver()
                }
            }
        }
    }

    private fun removeObserver() {
        viewModel.modules.removeObserver(initObserver)
    }

    private fun getLastReadFromModules(moduleEntities: List<ModuleEntity>): String? {
        var lastReadModule:String?=null
        for(module in moduleEntities) {
            if(module.isRead){
                lastReadModule=module.moduleId
                continue
            }
            break
        }
        return lastReadModule
    }

    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        fun launch(activity: Activity?, courseId: String, finish: Boolean) {
            activity?.apply {
                startActivity(Intent(this, CourseReaderActivity::class.java).apply {
                    putExtra(EXTRA_COURSE_ID, courseId)
                    if (finish)
                        finish()
                })
            }
        }

        const val EXTRA_COURSE_ID = "EXTRA_COURSE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)
        viewModel=ViewModelProviders.of(this,ViewModelFactory.getInstance(application)).get(CourseReaderViewModel::class.java)

        viewModel.modules.observe(this,initObserver)

        intent?.extras?.getString(EXTRA_COURSE_ID)?.let{
            viewModel.setCourseId(it)
            populateFragment()
        }
    }

    private fun populateFragment() {
        ModuleListFragment.attach(this,R.id.frame_container)
    }

    override fun moveTo(position: Int, moduleId: String) {
        Log.d("systemofwdown-activity",moduleId)
        ModuleContentFragment.attach(this, R.id.frame_container)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else
            super.onBackPressed()
    }
}
