package app.interactive.academy.ui.reader.list

import android.view.View
import android.view.ViewGroup
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
class ModuleListAdapter(private val onItemClicked:(positin:Int,moduleId:String)->Unit):BaseAdapter<ModuleEntity>(R.layout.items_module_list_custom) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderAdapter<ModuleEntity> =
        ViewHolder(getLayout(parent))

    inner class ViewHolder(view:View):BaseViewHolderAdapter<ModuleEntity>(view){
        private val txtTitle=view.findViewById<TextView>(R.id.text_module_title)
        private val txtLastSeen=view.findViewById<TextView>(R.id.text_last_seen)

        override fun onBind(data: ModuleEntity) {
            super.onBind(data)
            txtTitle.text=data.title
//            txtLastSeen.text=data.

            onItemClicked{
                onItemClicked(adapterPosition,data.moduleId)
            }
        }
    }
}