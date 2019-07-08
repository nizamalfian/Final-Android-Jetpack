package app.interactive.academy.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by L
 *
 * on 7/8/2019
 */
@Parcelize
data class CourseResponse(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val imagePath: String
) : Parcelable