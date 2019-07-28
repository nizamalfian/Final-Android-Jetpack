package app.interactive.academy.ui.academy


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.vo.Status
import app.interactive.academy.ui.detail.DetailCourseActivity
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.utils.gone
import app.interactive.academy.data.source.vo.Status.*
import app.interactive.academy.utils.visible

/**
 * A simple [Fragment] subclass.
 *
 */
class AcademyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: AcademyViewModel
    private lateinit var courses: List<CourseEntity>
    private lateinit var adapter: AcademyAdapter

    companion object {
        fun newInstance(): Fragment = AcademyFragment()
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
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_academy)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { fragmentActivity ->
            viewModel =
                ViewModelProviders.of(fragmentActivity, ViewModelFactory.getInstance(fragmentActivity.application))
                    .get(AcademyViewModel::class.java)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(fragmentActivity)
                setHasFixedSize(true)
                this@AcademyFragment.adapter = AcademyAdapter {
                    DetailCourseActivity.launch(fragmentActivity, it, false)
                }.also {
                    adapter = it
                }
            }

            viewModel.also { vm ->
                vm.setUsername("Dicoding")
                vm.getCourses().observe(this,
                    Observer{ courses ->
                        Log.d("backtoyou","wicked ${courses==null}")
                        if(courses!=null){
                            when(courses.status){
                                LOADING -> progressBar.visible()
                                SUCCESS ->{
                                    courses.data?.let{this@AcademyFragment.courses =it}
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
        }
    }
}
