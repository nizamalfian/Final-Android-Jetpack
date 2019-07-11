package app.interactive.academy.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.ui.reader.CourseReaderActivity
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.utils.gone
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import kotlinx.android.synthetic.main.activity_detail_course.*
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL as VERTICAL

class DetailCourseActivity : AppCompatActivity() {
    private lateinit var btnStart: Button
    private lateinit var txtTitle: TextView
    private lateinit var txtDesc: TextView
    private lateinit var txtDate: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var imagePoster: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: DetailCourseViewModel
    private lateinit var modules: List<ModuleEntity>

    companion object {
        fun launch(activity: Activity?, courseId: String, finish: Boolean) {
            activity?.apply {
                startActivity(Intent(this, DetailCourseActivity::class.java).apply {
                    putExtra(EXTRA_COURSE, courseId)
                    if (finish)
                        finish()
                })
            }
        }

        const val EXTRA_COURSE = "EXTRA_COURSE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel=ViewModelProviders.of(this,ViewModelFactory.getInstance(application)).get(DetailCourseViewModel::class.java)

        progressBar = findViewById(R.id.progress_bar)
        btnStart = findViewById(R.id.btn_start)
        txtTitle = findViewById(R.id.text_title)
        txtDesc = findViewById(R.id.text_description)
        txtDate = findViewById(R.id.text_date)
        recyclerView = findViewById(R.id.rv_module)
        imagePoster = findViewById(R.id.image_poster)

        recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            adapter = DetailCourseAdapter().apply {
                intent?.extras?.getString(EXTRA_COURSE)?.let {
                    viewModel.courseId=it
                    viewModel.getModules().observe(this@DetailCourseActivity,
                        Observer<ArrayList<ModuleEntity>>{
                            progressBar.gone()
                            modules=it.also{
                                updateData(it)
                            }
                        })
//                    modules=viewModel.getModules()
//                    updateData(modules)
                }
            }
            viewModel.getCourse().observe(this@DetailCourseActivity,
                Observer<CourseEntity>{
                    populateCourse(it)
                })
            addItemDecoration(DividerItemDecoration(this.context, VERTICAL))
        }
    }

    private fun populateCourse(course: CourseEntity) {
        course.also {
            Glide.with(applicationContext)
                .load(it?.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imagePoster)
            txtTitle.text = it?.title
            txtDesc.text = it?.description
            txtDate.text = String.format("Deadline %s", it?.deadline)

            btnStart.setOnClickListener {
                CourseReaderActivity.launch(this, course.courseId, false)
            }
        }
    }
}
