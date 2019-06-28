package app.interactive.academy.ui.reader.list


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.ModuleEntity
import app.interactive.academy.ui.detail.CourseReaderCallback
import app.interactive.academy.ui.reader.CourseReaderActivity
import app.interactive.academy.utils.generateDummyModules
import app.interactive.academy.utils.visible
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class ModuleListFragment : Fragment() {
    private lateinit var adapter:ModuleListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var callback:CourseReaderCallback

    companion object{
        val TAG=ModuleListFragment::class.java.simpleName
        private fun newInstance(): Fragment = ModuleListFragment()
        fun attach(activity: AppCompatActivity, @IdRes container:Int, fragment:Fragment= newInstance()){
            activity.run {
                supportFragmentManager
                    .beginTransaction()
                    .add(container,fragment,TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_module)
        progressBar=view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter= ModuleListAdapter{ position, moduleId->
            callback.moveTo(position,moduleId)
        }
        populateRecyclerView(generateDummyModules("a14"))
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback=activity as CourseReaderCallback
    }

    private fun populateRecyclerView(modules: ArrayList<ModuleEntity>) {
        progressBar.visible()
        adapter.updateData(modules)
        recyclerView.run{
            layoutManager=LinearLayoutManager(this@ModuleListFragment.context)
            adapter=adapter
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }
    }
}
