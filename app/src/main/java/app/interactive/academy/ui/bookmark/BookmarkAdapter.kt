package app.interactive.academy.ui.bookmark

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.interactive.academy.R
import app.interactive.academy.base.BaseAdapter
import app.interactive.academy.base.BaseViewHolderAdapter
import app.interactive.academy.data.CourseEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by L
 *
 * on 6/26/2019
 */
class BookmarkAdapter(private val onClick:(course:CourseEntity,isShareButton:Boolean)->Unit):BaseAdapter<CourseEntity>(R.layout.items_bookmark) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderAdapter<CourseEntity> =
        ViewHolder(getLayout(parent))

    inner class ViewHolder(view:View):BaseViewHolderAdapter<CourseEntity>(view){
        private val tvTitle=view.findViewById<TextView>(R.id.tv_item_title)
        private val tvDescription=view.findViewById<TextView>(R.id.tv_item_description)
        private val tvDate =view.findViewById<TextView>(R.id.tv_item_date)
        private val imgShare =view.findViewById<ImageView>(R.id.img_share)
        private val imgPoster  =view.findViewById<ImageView>(R.id.img_poster)

        override fun onBind(data: CourseEntity) {
            super.onBind(data)
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

            onItemClicked{
                onClick(data,false)
            }
            imgShare.setOnClickListener {
                onClick(data,true)
            }
        }
    }
}