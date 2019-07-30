package app.interactive.academy.ui.reader.list


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Status
import app.interactive.academy.ui.detail.CourseReaderCallback
import app.interactive.academy.utils.gone
import app.interactive.academy.ui.reader.CourseReaderViewModel
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import java.util.ArrayList
import app.interactive.academy.data.source.vo.Status.*
import app.interactive.academy.utils.visible

/**
 * A simple [Fragment] subclass.
 *
 */
class ModuleListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var callback:CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    companion object{
        val TAG=ModuleListFragment::class.java.simpleName
        fun newInstance(): Fragment = ModuleListFragment()
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
        activity?.let{
            viewModel=ViewModelProviders.of(it,ViewModelFactory.getInstance(it.application)).get(CourseReaderViewModel::class.java).also{
                it.modules.observe(this@ModuleListFragment,Observer{
                    if(it!=null){
                        when(it.status){
                            LOADING -> progressBar.visible()
                            SUCCESS ->{
                                populateRecyclerView(it.data)
                                progressBar.gone()
                            }
                            ERROR->{
                                Toast.makeText(activity,"Something error happened", Toast.LENGTH_SHORT).show()
                                progressBar.gone()
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback=activity as CourseReaderCallback
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>?) {
        recyclerView.run{
            layoutManager=LinearLayoutManager(this@ModuleListFragment.context)
            adapter=ModuleListAdapter{ position, moduleId->
                callback.moveTo(position,moduleId)
            }.apply{
                modules?.let{updateData(it)}
            }
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }
    }
}
