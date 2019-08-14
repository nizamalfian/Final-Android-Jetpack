package com.nizamalfian.androidjetpack.data.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by L
 *
 * on 7/8/2019
 */
@Parcelize
data class ModuleResponse(val moduleId:String,
                          val courseId:String,
                          val title:String,
                          val position:Int):Parcelable