package com.nizamalfian.androidjetpack.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.utils.loadImage

/**
 * Created by nizamalfian on 05/07/2019.
 */
class MovieAdapter(private val onItemClicked:(movieId:Int,isTVShow:Boolean)->Unit):RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val movies = ArrayList<MovieEntity>()

    fun update(movies:List<MovieEntity>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie,parent,false))
    }

    override fun getItemCount(): Int = this.movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(this.movies[position])
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val image=view.findViewById<ImageView>(R.id.image)
        private val title=view.findViewById<TextView>(R.id.txtTitle)
        private val rate=view.findViewById<TextView>(R.id.txtRate)
        private val date=view.findViewById<TextView>(R.id.txtDate)

        fun onBind(movie: MovieEntity){
            itemView.context.loadImage(movie.posterPath,image)
            title.text=movie.title
            rate.text=movie.voteAverage.toString()
            date.text=movie.releaseDate

            itemView.setOnClickListener {
                onItemClicked(movie.movieId,movie.isTVShow)
            }
        }
    }
}