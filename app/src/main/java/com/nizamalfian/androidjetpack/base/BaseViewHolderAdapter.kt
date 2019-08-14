package com.nizamalfian.androidjetpack.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by L
 *
 * on 6/26/2019
 */
open class BaseViewHolderAdapter<T:Any>(view:View):RecyclerView.ViewHolder(view) {
    private lateinit var data:T
    open fun onBind(data:T){
        this.data=data
    }
    open fun onItemClicked(clickAction:()->Unit){
        itemView.setOnClickListener {clickAction()}
    }
}