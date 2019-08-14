package com.nizamalfian.androidjetpack.ui.detail_

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.data.local.entity.CourseEntity
import com.nizamalfian.androidjetpack.data.local.entity.ModuleEntity
import com.nizamalfian.androidjetpack.ui.reader.CourseReaderActivity
import com.nizamalfian.androidjetpack.viewmodel.ViewModelFactory
import com.nizamalfian.androidjetpack.utils.gone
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import kotlinx.android.synthetic.main.activity_detail_course.*
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL as VERTICAL
import com.nizamalfian.androidjetpack.data.vo.Status.*
import com.nizamalfian.androidjetpack.utils.visible

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
    private var menu: Menu?=null

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
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application))
            .get(DetailCourseViewModel::class.java)

        progressBar = findViewById(R.id.progress_bar)
        btnStart = findViewById(R.id.btn_start)
        txtTitle = findViewById(R.id.text_title)
        txtDesc = findViewById(R.id.text_description)
        txtDate = findViewById(R.id.text_date)
        recyclerView = findViewById(R.id.rv_module)
        imagePoster = findViewById(R.id.image_poster)

        intent.extras?.let {
            it.getString(EXTRA_COURSE).run {
                this?.let {
                    viewModel.setCourseId(this)
                }
            }
        }

        recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            adapter = DetailCourseAdapter().apply {
                intent?.extras?.getString(EXTRA_COURSE)?.let {
                    viewModel.courseModule.observe(this@DetailCourseActivity, Observer { courseWithModuleResource ->
                        if (courseWithModuleResource != null) {
                            when (courseWithModuleResource.status) {
                                LOADING -> progressBar.visible()
                                SUCCESS -> {
                                    courseWithModuleResource.data?.let {
                                        updateData(it.modules)
                                        populateCourse(it.course)
                                        progressBar.gone()
                                    }
                                }
                                ERROR -> progressBar.gone()
                            }
                        }
                    })
                }
            }
            addItemDecoration(DividerItemDecoration(this.context, VERTICAL))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        this.menu=menu
        viewModel.courseModule.observe(this,Observer{courseWithModule->
            courseWithModule?.let{
                when(courseWithModule.status){
                    LOADING -> progressBar.visible()
                    SUCCESS -> {
                        courseWithModule.data?.let{
                            val state=it.course?.isBookmarked
                            setBookmarkState(state)
                            progressBar.gone()
                        }
                    }
                    ERROR -> {
                        Toast.makeText(this@DetailCourseActivity,"Something error happened",Toast.LENGTH_SHORT).show()
                        progressBar.gone()
                    }
                }
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId==R.id.action_bookmark){
            viewModel.setBookmark()
            true
        }else{
            onBackPressed()
            true
        }
    }

    private fun setBookmarkState(state: Boolean?) {
        if(menu==null)
            return
        val menuItem=menu?.findItem(R.id.action_bookmark)
        menuItem?.let{item->
            state?.let{item.setIcon(ContextCompat.getDrawable(this,if(it)R.drawable.ic_bookmarked_white else R.drawable.ic_bookmark_white))}
        }
    }

    private fun populateCourse(course: CourseEntity?) {
        course?.let{
            Glide.with(applicationContext)
                .load(it.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(imagePoster)
            txtTitle.text = it.title
            txtDesc.text = it.description
            txtDate.text = String.format("Deadline %s", it.deadline)

            btnStart.setOnClickListener {_->
                CourseReaderActivity.launch(this, it.courseId, false)
            }
        }
    }
}
