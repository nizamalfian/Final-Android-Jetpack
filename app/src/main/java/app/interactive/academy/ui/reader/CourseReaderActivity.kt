package app.interactive.academy.ui.reader

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.interactive.academy.R
import app.interactive.academy.ui.detail.CourseReaderCallback
import app.interactive.academy.ui.reader.content.ModuleContentFragment
import app.interactive.academy.ui.reader.list.ModuleListFragment

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
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

        val courseId=intent?.extras?.getString(EXTRA_COURSE_ID)
        if(courseId!=null)
            populateFragment()
    }

    private fun populateFragment() {
        ModuleListFragment.attach(this,R.id.frame_container)
    }

    override fun moveTo(position: Int, moduleId: String) {
        ModuleContentFragment.attach(this, R.id.frame_container)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1)
            finish()
        else
            super.onBackPressed()
    }
}
