package app.interactive.academy.ui.detail

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.interactive.academy.R
import app.interactive.academy.base.BaseAdapter
import app.interactive.academy.base.BaseViewHolderAdapter
import app.interactive.academy.data.source.local.entity.ModuleEntity

/**
 * Created by L
 *
 * on 6/28/2019
 */
class DetailCourseAdapter:BaseAdapter<ModuleEntity>(R.layout.items_module_list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderAdapter<ModuleEntity> =
        ViewHolder(getLayout(parent))

    inner class ViewHolder(view:View):BaseViewHolderAdapter<ModuleEntity>(view){
        private val txtTitle=view.findViewById<TextView>(R.id.text_module_title)

        override fun onBind(data: ModuleEntity) {
            super.onBind(data)
            txtTitle.text=data.title
        }
    }
}