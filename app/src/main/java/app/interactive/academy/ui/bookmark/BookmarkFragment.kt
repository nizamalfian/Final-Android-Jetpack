package app.interactive.academy.ui.bookmark


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.ui.detail.DetailCourseActivity
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.utils.gone

/**
 * A simple [Fragment] subclass.
 *
 */
class BookmarkFragment : Fragment() {
    private lateinit var adapter: BookmarkAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: BookmarkViewModel

    companion object {
        private fun newInstance(): Fragment = BookmarkFragment()
        fun attach(activity: AppCompatActivity, @IdRes container: Int, fragment: Fragment = newInstance()) {
            activity.run {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(container, fragment)
                    .commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_bookmark)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it, ViewModelFactory.getInstance(it.application))
                .get(BookmarkViewModel::class.java)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this@BookmarkFragment.adapter= BookmarkAdapter { course, isShareButton ->
                activity.apply {
                    if (isShareButton) {
                        val mimeType = "text/plain"
                        ShareCompat.IntentBuilder
                            .from(this)
                            .setType(mimeType)
                            .setChooserTitle("Bagikan aplikasi ini sekarang.")
                            .setText(String.format("Segera daftar kelas %s di dicoding.com", course.title))
                            .startChooser()
                    } else {
                        startActivity(Intent(this, DetailCourseActivity::class.java).apply {
                            putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                        })
                    }
                }
            }.also {
                adapter=it
            }
        }

        viewModel.getBookmarks().observe(this,
            Observer<ArrayList<CourseEntity>> { courses ->
                adapter.updateData(courses)
                progressBar.gone()
            })
    }

}
