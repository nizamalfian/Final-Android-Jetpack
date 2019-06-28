package app.interactive.academy.ui.detail

import android.view.View
import android.widget.TextView
import app.interactive.academy.R
import app.interactive.academy.base.BaseAdapter
import app.interactive.academy.base.BaseViewHolderAdapter
import app.interactive.academy.data.ModuleEntity

/**
 * Created by L
 *
 * on 6/28/2019
 */
class DetailCourseAdapter:BaseAdapter<ModuleEntity,DetailCourseAdapter.ViewHolder>(R.layout.items_module_list) {

    inner class ViewHolder(view:View):BaseViewHolderAdapter<ModuleEntity>(view){
        private val txtTitle=view.findViewById<TextView>(R.id.text_module_title)

        override fun onBind(data: ModuleEntity) {
            super.onBind(data)
            txtTitle.text=data.title
        }
    }
}