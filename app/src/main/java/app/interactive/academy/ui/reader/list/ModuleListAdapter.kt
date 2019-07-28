package app.interactive.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.base.BaseAdapter
import app.interactive.academy.base.BaseViewHolderAdapter
import app.interactive.academy.data.source.local.entity.ModuleEntity

/**
 * Created by L
 *
 * on 6/28/2019
 */
class ModuleListAdapter(private val onItemClicked:(position:Int,moduleId:String)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val modules:ArrayList<ModuleEntity> = ArrayList()

    override fun getItemCount(): Int = modules.size

    fun updateData(modules:List<ModuleEntity>){
        this.modules.addAll(modules)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val module = modules[position]
        if(holder.itemViewType==0){
            val moduleViewHolderHide = holder as ViewHolderHide
            moduleViewHolderHide.bind(module.title)
        }else{
            val viewHolder = holder as ViewHolder
            viewHolder.bind(module.title)
            viewHolder.itemView.setOnClickListener {
                onItemClicked(position,module.moduleId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType==0)
            ViewHolderHide(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom_disable,parent,false))
        else{
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom,parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val modulePosition=modules[position].position
        if(modulePosition==0)
            return 1
        else if(modules[modulePosition-1].isRead)
            return 1
        else return 0
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val txtTitle=view.findViewById<TextView>(R.id.text_module_title)

        fun bind(title:String) {
            txtTitle.text=title
        }
    }

    inner class ViewHolderHide(view:View):RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.text_module_title)

        fun bind(title:String){
            this.title.text=title
        }
    }
}