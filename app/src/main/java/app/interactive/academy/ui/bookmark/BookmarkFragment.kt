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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.ui.detail.DetailCourseActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class BookmarkFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var courses:List<CourseEntity>

    companion object {
        private fun newInstance(): Fragment = BookmarkFragment()
        fun attach(activity: AppCompatActivity, @IdRes container:Int, fragment:Fragment= newInstance()){
            activity.run {
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(container,fragment)
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
        activity?.let{
            viewModel=ViewModelProviders.of(it).get(BookmarkViewModel::class.java)
            courses=viewModel.getBookmarks()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = BookmarkAdapter{course,isShareButton->
                activity.apply{
                    if(isShareButton) {
                        val mimeType="text/plain"
                        ShareCompat.IntentBuilder
                            .from(this)
                            .setType(mimeType)
                            .setChooserTitle("Bagikan aplikasi ini sekarang.")
                            .setText(String.format("Segera daftar kelas %s di dicoding.com", course.title))
                            .startChooser()
                    }else{
                        startActivity(Intent(this, DetailCourseActivity::class.java).apply {
                            putExtra(DetailCourseActivity.EXTRA_COURSE,course.courseId)
                        })
                    }
                }
            }.apply {
                updateData(courses)
            }
        }
    }

}
