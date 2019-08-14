package com.nizamalfian.androidjetpack.ui.reader

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.data.local.entity.ModuleEntity
import com.nizamalfian.androidjetpack.data.vo.Resource
import com.nizamalfian.androidjetpack.ui.detail_.CourseReaderCallback
import com.nizamalfian.androidjetpack.ui.reader.content.ModuleContentFragment
import com.nizamalfian.androidjetpack.ui.reader.list.ModuleListFragment
import com.nizamalfian.androidjetpack.viewmodel.ViewModelFactory
import com.nizamalfian.androidjetpack.data.vo.Status.*

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    private var isLarge = false
    private val initObserver: Observer<Resource<List<ModuleEntity>>> = Observer { modules ->
        modules?.let {
            when (it.status) {
                LOADING -> {
                }
                SUCCESS -> {
                    it.data?.let { data ->
                        if (data.isNotEmpty()) {
                            val firstModule = data[0]
                            val isFirstModuleRead = firstModule.isRead
                            if (!isFirstModuleRead) {
                                viewModel.setSelectedModule(firstModule.moduleId)
                            } else {
                                getLastReadFromModules(data)?.let { moduleId ->
                                    viewModel.setSelectedModule(moduleId)
                                }
                            }
                        }
                    }
                }
                ERROR -> {
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
        var lastReadModule: String? = null
        for (module in moduleEntities) {
            if (module.isRead) {
                lastReadModule = module.moduleId
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

        if (findViewById<FrameLayout>(R.id.frame_list) != null)
            isLarge = true

        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application))
            .get(CourseReaderViewModel::class.java)

        viewModel.modules.observe(this, initObserver)

        intent?.extras?.getString(EXTRA_COURSE_ID)?.let {
            viewModel.setCourseId(it)
            populateFragment()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!isLarge) {
            var fragment: Fragment? = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
            if (fragment == null) {
                fragment = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
            }
            fragmentTransaction.commit()
        } else {
            /*var fragmentList: Fragment? = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
            if (fragmentList == null) {
                fragmentList = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragmentList, ModuleListFragment.TAG)
            }
            var frameContent: Fragment? = supportFragmentManager.findFragmentByTag(ModuleContentFragment.TAG)
            if (frameContent == null) {
                frameContent = ModuleContentFragment.newInstance()
                fragmentTransaction.add(R.id.frame_container, frameContent, ModuleContentFragment.TAG)
            }
            fragmentTransaction.commit()*/
            ModuleListFragment.attach(this, R.id.frame_list)
            ModuleContentFragment.attach(this, R.id.frame_content)
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        if (!isLarge) {
            Log.d("systemofwdown-activity", moduleId)
            ModuleContentFragment.attach(this, R.id.frame_container)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else
            super.onBackPressed()
    }
}
