package app.interactive.academy.ui.academy


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.ui.detail.DetailCourseActivity
import app.interactive.academy.utils.generateDummyCourses

/**
 * A simple [Fragment] subclass.
 *
 */
class AcademyFragment : Fragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var progressBar:ProgressBar

    companion object{
        fun newInstance():Fragment=AcademyFragment()
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
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_academy)
        progressBar=view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let{
            recyclerView.apply{
                layoutManager=LinearLayoutManager(it)
                setHasFixedSize(true)
                adapter=AcademyAdapter{courseId->
                    DetailCourseActivity.launch(it,courseId,false)
                }.apply{
                    updateData(generateDummyCourses())
                }
            }
        }
    }

}
