package app.interactive.academy.ui.reader.content


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import app.interactive.academy.R
import app.interactive.academy.data.ModuleEntity
import app.interactive.academy.viewmodel.CourseReaderViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class ModuleContentFragment : Fragment() {
    private lateinit var webView:WebView
    private lateinit var progressBar:ProgressBar
    private lateinit var viewModel:CourseReaderViewModel

    companion object{
        private val TAG: String =ModuleContentFragment::class.java.simpleName
        private fun newInstance(): Fragment = ModuleContentFragment()
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
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView=view.findViewById(R.id.web_view)
        progressBar=view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run{
            viewModel=ViewModelProviders.of(this).get(CourseReaderViewModel::class.java)
            populateWebView(viewModel.getSelectedModule())
        }
    }

    private fun populateWebView(content: ModuleEntity?) {
        webView.loadData(content?.contentEntity?.content,"text/html","UTF-8")
    }
}
