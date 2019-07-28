package app.interactive.academy.ui.reader.content


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.ui.reader.CourseReaderViewModel
import app.interactive.academy.ui.viewmodel.ViewModelFactory
import app.interactive.academy.utils.gone
import app.interactive.academy.data.source.vo.Status.*
import app.interactive.academy.utils.visible

/**
 * A simple [Fragment] subclass.
 *
 */
class ModuleContentFragment : Fragment() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnNext:Button
    private lateinit var btnPrev:Button
    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        private val TAG: String = ModuleContentFragment::class.java.simpleName
        private fun newInstance(): Fragment = ModuleContentFragment()
        fun attach(activity: AppCompatActivity, @IdRes container: Int, fragment: Fragment = newInstance()) {
            activity.run {
                supportFragmentManager
                    .beginTransaction()
                    .add(container, fragment, TAG)
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
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.web_view)
        progressBar = view.findViewById(R.id.progress_bar)
        btnNext = view.findViewById(R.id.btn_next)
        btnPrev = view.findViewById(R.id.btn_prev)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(CourseReaderViewModel::class.java)
            viewModel.selectedModule.observe(this,
                Observer {
                    it?.let{
                        when(it.status){
                            LOADING -> progressBar.visible()
                            SUCCESS -> {
                                it.data?.let{
                                    setButtonNextPrefState(it)
                                    if(!it.isRead)
                                        viewModel.readContent(it)
                                    it.contentEntity?.let{
                                        populateWebView(it)
                                    }
                                }
                            }
                            ERROR -> {
                                Toast.makeText(activity,"Something error happened", Toast.LENGTH_SHORT).show()
                                progressBar.gone()
                            }
                        }
                    }
                    btnNext.setOnClickListener{viewModel.setNextPage()}
                    btnPrev.setOnClickListener{viewModel.setPrevPage()}
                })
        }
    }

    private fun setButtonNextPrefState(module: ModuleEntity) {
        activity?.let{
            when(module.position){
                0->{
                    btnPrev.isEnabled=false
                    btnNext.isEnabled=true
                }
                viewModel.getModulesSize()-1->{
                    btnPrev.isEnabled=true
                    btnNext.isEnabled=false
                }
                else->{
                    btnPrev.isEnabled=true
                    btnNext.isEnabled=true
                }
            }
        }
    }

    private fun populateWebView(content: ContentEntity?) {
        Log.d("asdfadf-populateWebview"," -- "+content?.content)
        content?.let{
            webView.loadData(it.content, "text/html", "UTF-8")
            progressBar.gone()
        }
    }
}
