package app.interactive.academy.ui.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.interactive.academy.R
import app.interactive.academy.data.source.local.entity.CourseEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by L
 *
 * on 7/30/2019
 */
class BookmarkPagedAdapter(private val onClick:(course: CourseEntity, isShareButton:Boolean)->Unit):PagedListAdapter<CourseEntity,BookmarkPagedAdapter.ViewHolder>(DIFF_CALLBACK) {
    private val courses = ArrayList<CourseEntity>()

    companion object{
        private val DIFF_CALLBACK:DiffUtil.ItemCallback<CourseEntity> = object : DiffUtil.ItemCallback<CourseEntity>(){
            override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean =
                oldItem.courseId==newItem.courseId

            override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean =
                oldItem==newItem
        }
    }

    fun getItemById(swipePosition:Int):CourseEntity?=getItem(swipePosition)

    fun updateData(data: List<CourseEntity>?){
        data?.let {
            this@BookmarkPagedAdapter.courses.clear()
            this@BookmarkPagedAdapter.courses.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val tvTitle=view.findViewById<TextView>(R.id.tv_item_title)
        private val tvDescription=view.findViewById<TextView>(R.id.tv_item_description)
        private val tvDate =view.findViewById<TextView>(R.id.tv_item_date)
        private val imgShare =view.findViewById<ImageView>(R.id.img_share)
        private val imgPoster  =view.findViewById<ImageView>(R.id.img_poster)

        fun onBind(data: CourseEntity) {
            Glide
                .with(itemView.context)
                .load(data.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgPoster)
            tvTitle.text=data.title
            tvDescription.text=data.description
            tvDate.text= String.format("Deadline %s",data.deadline)

            itemView.setOnClickListener { onClick(data,false) }
            imgShare.setOnClickListener {
                onClick(data,true)
            }
        }
    }
}