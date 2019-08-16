package com.nizamalfian.androidjetpack.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

/**
 * Created by L
 *
 * on 7/3/2019
 */

fun Context.loadImage(url:String,imageView: ImageView){
    Glide.with(this).load(url).placeholder(ContextCompat.getDrawable(this,android.R.drawable.ic_menu_gallery)).into(imageView)
}