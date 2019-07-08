package app.interactive.academy.ui.academy

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.interactive.academy.R
import app.interactive.academy.base.BaseAdapter
import app.interactive.academy.base.BaseViewHolderAdapter
import app.interactive.academy.data.source.local.entity.CourseEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by L
 *
 * on 6/26/2019
 */
class AcademyAdapter(private val onItemClick:(String)->Unit):BaseAdapter<CourseEntity>(R.layout.items_academy){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=
        ViewHolder(getLayout(parent))

    inner class ViewHolder(view:View): BaseViewHolderAdapter<CourseEntity>(view) {
        private val tvTitle:TextView = view.findViewById(R.id.tv_item_title)
        private val tvDescription:TextView = view.findViewById(R.id.tv_item_description)
        private val tvDate:TextView = view.findViewById(R.id.tv_item_date)
        private val imgPoster:ImageView = view.findViewById(R.id.img_poster)

        override fun onBind(data: CourseEntity) {
            super.onBind(data)
            Glide
                .with(itemView.context)
                .load(data.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(imgPoster)
            tvTitle.text=data.title
            tvDescription.text=data.description
            tvDate.text= String.format("Deadline %s",data.deadline)

            onItemClicked{
                onItemClick(data.courseId)
            }
        }
    }
}


