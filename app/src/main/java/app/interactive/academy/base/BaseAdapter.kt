package app.interactive.academy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by L
 *
 * on 6/26/2019
 */
abstract class BaseAdapter<T : Any,baseViewHolder:BaseViewHolderAdapter<T>>(@LayoutRes val layout: Int) :
        RecyclerView.Adapter<BaseViewHolderAdapter<T>>() {
    private val data:ArrayList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): baseViewHolder =
        BaseViewHolderAdapter<T>(getLayout(parent)) as baseViewHolder

    private fun ViewGroup.view(@LayoutRes layout:Int):View=LayoutInflater.from(this.context).inflate(layout,this,false)

    private fun getLayout(parent:ViewGroup):View =
        parent.view(layout)

    fun getData():ArrayList<T> = data

    open fun updateData(data: List<T>?){
        data?.let {
            clear()
            this.data.addAll(it)
        }
        notifyDataSetChanged()
    }

    open fun clear(){
        data.clear()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolderAdapter<T>, position: Int) {
        holder.onBind(data[position])
    }

}