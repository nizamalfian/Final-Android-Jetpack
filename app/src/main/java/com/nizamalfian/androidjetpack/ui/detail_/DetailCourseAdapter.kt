package com.nizamalfian.androidjetpack.ui.detail_

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.base.BaseAdapter
import com.nizamalfian.androidjetpack.base.BaseViewHolderAdapter
import com.nizamalfian.androidjetpack.data.local.entity.ModuleEntity

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