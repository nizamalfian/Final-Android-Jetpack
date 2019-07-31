package app.interactive.academy.ui.bookmark


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.ui.detail.DetailCourseActivity
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.utils.gone
import app.interactive.academy.data.source.vo.Status.*
import app.interactive.academy.utils.visible
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 *
 */
class BookmarkFragment : Fragment() {
    private lateinit var adapter: BookmarkPagedAdapter
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

            itemTouchHelper.attachToRecyclerView(recyclerView)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this@BookmarkFragment.adapter= BookmarkPagedAdapter { course, isShareButton ->
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
            Observer{ courses ->
                courses?.let{
                    when(it.status){
                        LOADING -> progressBar.visible()
                        SUCCESS -> {
                            adapter.updateData(courses.data)
                            progressBar.gone()
                        }
                        ERROR -> {
                            progressBar.gone()
                            Toast.makeText(activity,"Something error happened",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback(){
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            view?.let{
                adapter.getItemById(viewHolder.adapterPosition)?.run{
                    viewModel.setBookmark(this)
                    Snackbar.make(it,R.string.message_undo,Snackbar.LENGTH_LONG)
                        .setAction(R.string.message_ok){ viewModel.setBookmark(this)}
                        .show()
                }
            }
        }
    })

}
