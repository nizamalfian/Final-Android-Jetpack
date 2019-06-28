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
import androidx.fragment.app.FragmentTransaction
import app.interactive.academy.R
import app.interactive.academy.data.ContentEntity

/**
 * A simple [Fragment] subclass.
 *
 */
class ModuleContentFragment : Fragment() {
    private lateinit var webView:WebView
    private lateinit var progressBar:ProgressBar

    companion object{
        val TAG=ModuleContentFragment::class.java.simpleName
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
            ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>").also{
                populateWebView(it)
            }
        }
    }

    private fun populateWebView(entity: ContentEntity) {
        webView.loadData(entity.content,"text/html","UTF-8")
    }
}
